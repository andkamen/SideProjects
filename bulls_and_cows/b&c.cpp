#include <iostream>
#include <string>
#include <cstring>
#include <sstream>//transfer from stream to int etc
#include <random>
using namespace std;

struct Node {
	string data;
	Node* next;
};

//this is bad practice. Find a way around it. (make a class for all the linked list stuff maybe??)
struct Node *copyHead;
struct Node *head = new Node;

// only for the 1st Node
void initNode(struct Node *head, string n){
	head->data = n;
	head->next = NULL;
}

// apending
void addNode(struct Node *head, string n) {
	Node *newNode = new Node;
	newNode->data = n;
	newNode->next = NULL;

	Node *cur = head;
	while (cur) {
		if (cur->next == NULL) {
			cur->next = newNode;
			return;
		}
		cur = cur->next;
	}
}

struct Node *searchNode(struct Node *head, string n) {
	Node *cur = head;
	while (cur) {
		if (cur->data == n) return cur;
		cur = cur->next;
	}
	cout << "No Node " << n << " in list.\n";
}

bool deleteNode(struct Node **head, Node *ptrDel) {
	Node *cur = *head;
	if (ptrDel == *head) {
		*head = cur->next;
		delete ptrDel;
		return true;
	}

	while (cur) {
		if (cur->next == ptrDel) {
			cur->next = ptrDel->next;
			delete ptrDel;
			return true;
		}
		cur = cur->next;
	}
	return false;
}

/* Creating a copy of a linked list */
void copyLinkedList(struct Node *node, struct Node **pNew)
{
	if (node != NULL) {
		*pNew = new Node;
		(*pNew)->data = node->data;
		//cout << node->data << endl;
		(*pNew)->next = NULL;
		copyLinkedList(node->next, &((*pNew)->next));
	}
}

void deleteLinkedList(struct Node **node)
{
	struct Node *tmpNode;
	while (*node) {
		tmpNode = *node;
		*node = tmpNode->next;
		delete tmpNode;
	}
}

void display(struct Node *head) {
	Node *list = head;
	while (list) {
		cout << list->data << " ";
		list = list->next;
	}
	cout << endl;
	cout << endl;
}

//checks if the guess is a valid one. 4 long, no repeating digits, doesn't start with 0
bool guess_validity(string guess, bool feedback){

	if (guess.length() != 4){
		if (feedback == true){
			cout << "Guess is of the wrong size. It should be 4 digits long." << endl;
		}
		return false;
	}

	if (guess[0] == '0') {
		if (feedback == true){
			cout << "Guess can't begin with a 0." << endl;
		}
		return false;
	}

	for (int i = 0; i < 4; i++){
		for (int k = i + 1; k < 4; k++){
			if (guess[i] == guess[k]){
				if (feedback == true){
					cout << "Guess can't have repeating digits." << endl;
				}
				return false;
			}
		}
	}
	return true;
}

//randomly pick a number for the player to guess. 
//Since not all numbers are allowed, roll till a number is.
string pc_number(){

	random_device rd;
	mt19937 rng(rd());
	uniform_int_distribution<int> uni(1023, 9876);

	string pc_number;

	do{
		int pc_choice = uni(rng);
		pc_number = to_string(pc_choice);

	} while (guess_validity(pc_number, false) == false);

	return pc_number;
}

//Create the linked list that will hold all possible values for the guess
void createList(){
	string number_str; //string containing all for digits for the number
	const char *num;
	int counter = 1;

	//those embeded for loops create all the possible combinations 
	//of numbers without repeating digits or starting with 0
	for (int i = 1; i <= 9; i++){
		for (int j = 0; j <= 9; j++){
			if (i == j) continue;
			for (int k = 0; k <= 9; k++){
				if ((i == k) || (j == k)) continue;
				for (int l = 0; l <= 9; l++){
					if ((i == l) || (j == l) || (k == l)) continue;

					number_str.clear();
					number_str.append(to_string(i));
					number_str.append(to_string(j));
					number_str.append(to_string(k));
					number_str.append(to_string(l));

					num = number_str.c_str();

					if (counter == 1){
						initNode(head, number_str);
					}
					else addNode(head, number_str);

					counter++;
				}
			}
		}
	}
}

//PC makes 1 guess for the player's number and eliminates impossible guesses
bool pc_guess() {
	Node *list = head;
	Node *ptrDelete; // used to delete nodes from list 2

	int length = 0, counter = 0; //help find the randomly picked number
	int num_pos; // position of the guessed number in the linked list
	int bulls = 0, cows = 0;// number of bulls and cows in pc's guess
	int bulls_check = 0, cows_check = 0; // when comparing guess to number from list to see if its a valid choice
	string answer;//user answer if guess is right or wrong
	string guess; //pc's guess for player's number
	string list_num; // number from list to be compared to the guess

	//finds the length of the linked list
	while (list) {
		list = list->next;
		length++;
	}
	//cout << "length " << length << endl;
	list = head; //preparing list for next read through

	random_device rd;
	mt19937 rng(rd());
	uniform_int_distribution<int> uni(0, length - 1); //since length is counted from 0, cant got 1 over. 

	num_pos = uni(rng); //picks the number from the list

	//cout << "num position " << num_pos<< endl;
	//finds the number on the picked position
	while (list){
		//	cout << "counter " << counter<< " num pos " << num_pos<< " number " << list->data << endl;
		if (counter == num_pos){
			guess = list->data;
			break;
		}
		list = list->next;
		counter++;
		//	system("pause");
	}

	list = head;

	cout << "Is your number " << guess << " ?(Y/N)" << endl;

	do{
		cin >> answer;

		//add counter to show message if player doesn't guess the pc's num on following turn
		if (answer == "y" || answer == "Y"|| answer == "yes" || answer == "Yes"){
			cout << "Computer has guessed your number: " << guess << " You have one guess left to tie" << endl;
			return true;
		}
		else if (answer == "n" || answer == "N" || answer == "no" || answer == "No"){
			do{
				cout << "How many bulls?... " << endl;
				cin >> bulls;
				cout << "How many cows?... " << endl;
				cin >> cows;
				if ((bulls + cows) > 4) cout << "Invalid number of bulls and cows. Give a correct answer" << endl;

			} while ((bulls + cows) > 4);
			break;
		}
		else {
			cout << "Please put a clear answer y/n" << endl;// will repeat multiple times for each char in the typo	
		}
	} while (answer != "y" || answer != "n");



	copyLinkedList(head, &copyHead); // make a copy of the list of possible numbers. Go through list 1 and every number that 
	//is not viable will be deleted from list 2. Then list 1=list 2. 

	while (list) {
		list_num = list->data;

		for (int i = 0; i < 4; i++){
			if (guess[i] == list_num[i]){
				bulls_check++;
				continue;
			}
			for (int k = 0; k < 4; k++){
				if (k == i) continue;
				else if (guess[k] == list_num[i])
					cows_check++;
			}
		}

		if (bulls_check != bulls || cows_check != cows || (bulls_check + cows_check) != (bulls + cows)){
			ptrDelete = searchNode(copyHead, list_num); //find the not viable entry in list 2 and delete it
			deleteNode(&copyHead, ptrDelete);
		}

		list = list->next;
		bulls_check = 0;
		cows_check = 0;
	}
	list = head;
	copyLinkedList(copyHead, &head); // make list 1 = the new trimmed list 2
	deleteLinkedList(&copyHead); // no longer needed
	//display(head);
	length = 0;

	return false;
}

bool user_guess(string pc_num){

	string guess;

	int bulls = 0;
	int cows = 0;

	cout << "Guess a number: " << endl;
	cin >> guess;

	/*cheat code if you are too lasy  */
	if (guess == "upupdowndownleftrightleftrightba") cout << "The PC's number is: " << pc_num << endl;


	while (guess_validity(guess, true) == false){
		cout << "Input new guess." << endl;
		cin >> guess;
	}

	for (int i = 0; i < 4; i++){
		if (guess[i] == pc_num[i]){
			bulls++;

		}
		for (int k = 0; k < 4; k++){
			if (k == i) continue;
			else if (guess[k] == pc_num[i])
				cows++;
		}
	}

	cout << "bulls: " << bulls << " cows: " << cows << endl;
	if (bulls == 4){
		cout << "Congratulations! You guessed the PC's number!" << endl;
		return true;
	}


	return false;
}

int main()
{
	string pc_num;
	char continue_game;
	bool pc_win_condition = false; //when this is true, the pc has guessed the players number
	bool user_win_condition = false;

	cout << "Welcome to a game of \"Bulls and Cows\"!\nThe aim of the game is to guess your opponents number.\n";
	cout << "The number is made of 4 non-repeating digits and can't start with 0." << endl;
	cout << "Bulls represent the number of right digits in the right place.\n";
	cout << "Cows represent the number of right digits in the wrong place. \nUse those clues to guess the pc's number."<<endl;
	cout << "Before the start of the game think of a number for the computer to guess. " << endl;

	while (true){
		cout << endl;
		cout << "       STARTING NEW GAME!       " << endl;
		cout<<endl;

		createList();
		pc_num = pc_number();
		pc_win_condition = false;
		user_win_condition = false;

		while (pc_win_condition == false && user_win_condition == false){
			pc_win_condition = pc_guess();
			user_win_condition = user_guess(pc_num);
		}

		if (user_win_condition > pc_win_condition){
			cout << "Congratulations you won the game!" << endl;
		}
		else if (user_win_condition == pc_win_condition){

			cout << "You tied with the computer!" << endl;
		}
		else if (user_win_condition < pc_win_condition){

			cout << "You have lost the game. Do you want to continue guessing the computer's number? " << endl;
			cin >> continue_game;

			if (continue_game == 'y' || continue_game == 'Y' || continue_game == 'yes' || continue_game == 'Yes'){

				while (user_win_condition == false){
					user_win_condition = user_guess(pc_num);
				}
				if (user_win_condition == true){
					cout << "Congratulations, you finally guessed the pc's number." << endl;
				}
			}
			else if (continue_game == 'n' || continue_game == 'N' || continue_game == 'no' || continue_game == 'No'){
				cout << "Thanks for playing." << endl;
			}
		}

		cout << "Do you want to play again?" << endl;
		cin >> continue_game;
		if (continue_game == 'y' || continue_game == 'Y' || continue_game == 'yes' || continue_game == 'Yes'){
			continue;
		}
		else if (continue_game == 'n' || continue_game == 'N' || continue_game == 'no' || continue_game == 'No'){
			cout << "Thanks for playing." << endl;
			break;
		}
	}

	deleteLinkedList(&head);
	system("pause");
	return 0;
}
