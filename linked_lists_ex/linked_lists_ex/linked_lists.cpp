#include <iostream>
#include <string>

using namespace std;

struct Node {
	string data;
	Node* next;
};

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

void insertFront(struct Node **head, string n) {
	Node *newNode = new Node;
	newNode->data = n;
	newNode->next = *head;
	*head = newNode;
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

/* reverse the list */
struct Node* reverse(struct Node** head)
{
	Node *parent = *head;
	Node *me = parent->next;
	Node *child = me->next;

	/* make parent as tail */
	parent->next = NULL;
	while (child) {
		me->next = parent;
		parent = me;
		me = child;
		child = child->next;
	}
	me->next = parent;
	*head = me;
	return *head;
}

/* Creating a copy of a linked list */
void copyLinkedList(struct Node *node, struct Node **pNew)
{
	if (node != NULL) {
		*pNew = new Node;
		(*pNew)->data = node->data;
		(*pNew)->next = NULL;
		copyLinkedList(node->next, &((*pNew)->next));
	}
}

/* Compare two linked list */
/* return value: same(1), different(0) */
int compareLinkedList(struct Node *node1, struct Node *node2)
{
	static int flag;

	/* both lists are NULL */
	if (node1 == NULL && node2 == NULL) {
		flag = 1;
	}
	else {
		if (node1 == NULL || node2 == NULL)
			flag = 0;
		else if (node1->data != node2->data)
			flag = 0;
		else
			compareLinkedList(node1->next, node2->next);
	}

	return flag;
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

void remove(struct Node *head, string val)
{
	Node *pPre = NULL, *pDel = NULL, *pTail = NULL;

	/* Check whether it is the head node?
	if it is, delete and update the head node */
	if (head->data == val) {
		/* point to the node to be deleted */
		pDel = head;
		/* update */
		head = pDel->next;
		delete pDel;
		return;
	}

	pPre = head;
	pDel = head->next;

	/* traverse the list and check the value of each node */
	while (pDel != NULL) {
		if (pDel->data == val) {
			/* Update the list */
			pPre->next = pDel->next;
			/* If it is the last node, update the tail */
			if (pDel == pTail) {
				pTail = pPre;
			}
			delete pDel; /* Here only remove the first node with the given value */
			break; /* break and return */
		}
		pPre = pDel;
		pDel = pDel->next;
	}
}







int main()
{
	struct Node *copyHead;
	struct Node *head = new Node;


	initNode(head, to_string(0));
	for (int i = 1; i <= 3909; i++){
		string num = to_string(i);

		addNode(head, num);

	}
	cout << "actual list: " << endl;
	display(head);

	cout << "coppied list: " << endl;
	copyLinkedList(head, &copyHead);
	//display(copyHead);

	/*
	Node *list = head;

	while (list!=NULL) {

		string even;
		even = list->data;
		
		int even_check = stoi(even, nullptr, 0);

		if (even_check % 2 != 0){
			Node *ptrDelete = searchNode(copyHead, even);
			cout << "try and delete: " << even << endl;
			deleteNode(&copyHead, ptrDelete);
		}
		

		list = list->next;

	}
	cout << endl;
	*/
	cout << "actual list: " << endl;
	//display(head);

	cout << "coppied list: " << endl;
	//display(copyHead);

	//copyLinkedList(copyHead, &head);
	//display(head);

	//remove(list,even);
	//display(head);

	//remove(head, 2);
	//display(head);

	/*
	initNode(head, 10);
	display(head);

	addNode(head, 20);
	display(head);

	addNode(head, 30);
	display(head);

	addNode(head, 35);
	display(head);

	addNode(head, 40);
	display(head);

	insertFront(&head, 5);
	display(head);

	int numDel = 5;
	Node *ptrDelete = searchNode(head, numDel);
	if (deleteNode(&head, ptrDelete))
	cout << "Node " << numDel << " deleted!\n";
	display(head);

	cout << "The list is reversed\n";
	reverse(&head);
	display(head);

	cout << "The list is copied\n";
	copyLinkedList(head, &copyHead);
	display(copyHead);

	cout << "Comparing the two lists...\n";
	cout << "Are the two lists same?\n";
	if (compareLinkedList(head, copyHead))
	cout << "Yes, they are same!\n";
	else
	cout << "No, they are different!\n";
	cout << endl;

	numDel = 35;
	ptrDelete = searchNode(copyHead, numDel);
	if (deleteNode(&copyHead, ptrDelete)) {
	cout << "Node " << numDel << " deleted!\n";
	cout << "The new list after the delete is\n";
	display(copyHead);
	}
	cout << "Comparing the two lists again...\n";
	cout << "Are the two lists same?\n";
	if (compareLinkedList(head, copyHead))
	cout << "Yes, they are same!\n";
	else
	cout << "No, they are different!\n";

	cout << endl;
	cout << "Deleting the copied list\n";
	
	display(copyHead);


	*/
	deleteLinkedList(&head);
	deleteLinkedList(&copyHead);
	system("pause");
	return 0;
}