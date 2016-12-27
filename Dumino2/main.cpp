#include <iostream>
#include <string>
#include <vector>
#include <windows.h> // needed for bg output
#include <fstream> 
#include <algorithm>
#include <sstream> 
#include <iomanip> //loadbar

using namespace std;
typedef vector<vector<char>> vector_2dc;

//checks if a word is part of the dictionary
bool binarySearch(vector<string> &dictionary, int size, string search) {

    int first = 0,
            last = size - 1,
            middle,
            position = -1,
            counter = 1;
    bool found = false;

    while (first < last) {
        middle = (first + last) / 2;

        if (dictionary[middle] < search) {
            first = middle + 1;
        } else {
            last = middle;
        }
    }
    //at exit of while loop if list is empty
    //last<first, otherwise last==first

    if ((first == last) && (dictionary[first] == search)) {
        found = true;
        return found;
    } else {
        return found;
    }
}

//sets/resets the letters in the grid
void initialize_grid(vector_2dc &grid, int grid_height, int grid_width) {
    char letter;
    vector<char> grid_letters;

    cout << "Options-> Edit->Paste the letters for the current game: " << endl;
    for (int i = 0; i < grid_height * grid_width; i++) {
        cin >> letter;
        grid_letters.push_back(letter);
    }

    cout << endl;
    cout << "Current Letter Grid: " << endl;
    for (int r = 0; r < grid_height; ++r) {
        for (int c = 0; c < grid_width; ++c) {
            grid[r][c] = grid_letters[r * grid_width + c];
            cout << grid[r][c] << " ";
        }
        cout << endl;
    }
    cout << endl;
}

//fancy dictionary loading progress bar
static inline void loadbar(unsigned int x, unsigned int n, unsigned int width = 50) {
    if ((x != n) && (x % (n / 100 + 1) != 0)) return;

    float ratio = x / (float) n;
    int c = (int) (ratio * width);

    cout << setw(3) << (int) (ratio * 100) << "% [";
    for (int x = 0; x < c; x++) cout << "=";
    for (unsigned int x = c; x < width; x++) cout << " ";
    cout << "]\r" << flush;

}

//loads the dictionary words
void loadDictionary(vector<string> &list) {
    string line;
    ifstream file_input("wordlist.txt");
    if (file_input.is_open()) {
        std::cout << "Loading Dictionary. " << endl;
        while (getline(file_input, line)) {
            list.push_back(line);

            loadbar(list.size(), 882400, 50); //number of words in dictionary // increments of 2%
        }
        file_input.close();
    }
    std::cout << endl;
    std::cout << "Finished Loading Dictionary. " << endl;
}

//for better text file output
struct compare {
    bool operator()(const string &first, const string &second) {
        return first.size() < second.size();
    }
};

bool inRange(vector_2dc &grid, int row, int col) {
    bool rowInRange = row >= 0 && row < grid.size();
    bool colInRange = col >= 0 && col < grid[0].size();

    return rowInRange && colInRange;
}

bool findWordsInGrid(vector<string> &dictionary, vector<string> &foundWords, vector_2dc &grid,
                     vector<char *> letterSequence, string word, int row, int col, int maxDepth) {
    if (!inRange(grid, row, col)) {
        return false;
    }

    if (letterSequence.size() != 0) {
        //return if current letter has already been used in this word
        for (char *letter : letterSequence) {
            if (&grid[row][col] == letter) {
                return false;
            }
        }
    }
    letterSequence.push_back(&grid[row][col]);
    word.push_back(grid[row][col]);

    if (word.size() >= 3) {
        if (binarySearch(dictionary, dictionary.size(), word)) {
            foundWords.push_back(word);
            cout << word << endl;
        }
    }

    if (word.size() >= maxDepth) {
        return true;
    }
    //x8, once for each direction
    //up
    findWordsInGrid(dictionary, foundWords, grid, letterSequence, word, row - 1, col, maxDepth);

    //up-right diagonal
    findWordsInGrid(dictionary, foundWords, grid, letterSequence, word, row - 1, col + 1, maxDepth);

    //right
    findWordsInGrid(dictionary, foundWords, grid, letterSequence, word, row, col + 1, maxDepth);

    //bottom right diagonal
    findWordsInGrid(dictionary, foundWords, grid, letterSequence, word, row + 1, col + 1, maxDepth);

    //bottom
    findWordsInGrid(dictionary, foundWords, grid, letterSequence, word, row + 1, col, maxDepth);

    //bottom left diagonal
    findWordsInGrid(dictionary, foundWords, grid, letterSequence, word, row + 1, col - 1, maxDepth);

    //left
    findWordsInGrid(dictionary, foundWords, grid, letterSequence, word, row, col - 1, maxDepth);

    //upper left diagonal
    findWordsInGrid(dictionary, foundWords, grid, letterSequence, word, row - 1, col - 1, maxDepth);

    return true;
}

void func(int **array) {
    cout << array[0] << endl;
}

int main() {
    // allows console output in cyrillic
    SetConsoleCP(GetACP());
    SetConsoleOutputCP(GetACP());

    vector<string> dictionary; //list of existing words
    vector<string> foundWords; // list of current words

    int grid_height = 5;
    int grid_width = 6;
    int wordDepthFirstSearch = 8;
    int wordDepthSecondSearch = 10;

    vector_2dc grid(grid_height, vector<char>(grid_width)); //letter grid
    vector<char *> letterSequence;
    string word;

    bool isConsecutiveGame = false;

    //create the output file (or empty it since the last use)
    ofstream file_output;
    file_output.open("results.txt");
    file_output.close();

    loadDictionary(dictionary);//Fill the list with the ~1 million sorted dictionary words (takes about 1.5mins)

    while (true) {
        char playerInput;
        while (isConsecutiveGame) {
            std::cout << "Done using the results from last game? (y/n)" << endl;
            cin >> playerInput;
            if (playerInput == 'y' || playerInput == 'Y') {
                file_output.open("results.txt", ios::trunc);
                file_output.close();
                foundWords.clear();
                break;
            }
        }

        initialize_grid(grid, grid_height, grid_width);//set the letters

        //find shorter words up to 8 letters
        for (int r = 0; r < grid_height; ++r) {
            for (int c = 0; c < grid_width; ++c) {
                findWordsInGrid(dictionary, foundWords, grid, letterSequence, word, r, c, wordDepthFirstSearch);
            }
        }

        compare c_prelim;
        sort(foundWords.begin(), foundWords.end());
        foundWords.erase(unique(foundWords.begin(), foundWords.end()), foundWords.end());//remove repeating found words
        sort(foundWords.begin(), foundWords.end(), c_prelim);

        file_output.open("results.txt", ios::app);
        for (auto it = foundWords.begin(); it != foundWords.end(); it++) {
            file_output << *it << endl; //output unique words from current iteration
        }
        file_output.close();

        //look for longer words
        for (int r = 0; r < grid_height; ++r) {
            for (int c = 0; c < grid_width; ++c) {
                findWordsInGrid(dictionary, foundWords, grid, letterSequence, word, r, c, wordDepthSecondSearch);
            }
        }
        compare c;
        sort(foundWords.begin(), foundWords.end());
        foundWords.erase(unique(foundWords.begin(), foundWords.end()), foundWords.end());//remove repeating found words
        sort(foundWords.begin(), foundWords.end(), c);

        file_output.open("results.txt", ios::trunc);
        for (auto it = foundWords.begin(); it != foundWords.end(); it++) {
            file_output << *it << endl; //output unique words from current iteration
        }
        file_output.close();

        std::cout << "Do you want to play again? (y/n)" << endl;
        cin >> playerInput;
        if (playerInput == 'y' || playerInput == 'Y') {
            isConsecutiveGame = true;
            continue;
        } else if (playerInput == 'n' || playerInput == 'N') {
            std::cout << "Thanks for playing." << endl;
            break;
        }
    }

    return 0;
}