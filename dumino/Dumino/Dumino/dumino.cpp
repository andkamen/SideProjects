#include <iostream>
#include <string>
#include <vector>
#include <windows.h> // needed for bg output
#include <fstream> 
#include <algorithm>
#include <sstream> 
#include <iomanip>//loadbar

using namespace std;
typedef vector< vector<char> > vector_2dc;

//checks if a word is part of the dictionary

bool binarySearch(vector<string>& list, int size, vector<string> search)
{

	int first = 0,
		last = size - 1,
		middle,
		position = -1,
		counter = 1;
	bool found = false;

	while (first < last)
	{
		middle = (first + last) / 2;

		if (list[middle] < search[0])
			first = middle + 1;
		else
			last = middle;
	}
	//at exit of while loop if list is empty
	//last<first, otherwise last==first

	if ((first == last) && (list[first] == search[0]))
	{
		found = true;
		return found;
	}
	else
		return found;

}

//sets/resets the letters in the grid
void initialize_grid(vector_2dc &grid, int grid_length, int grid_width)
{
	char letter;
	vector < char > grid_letters;

	cout << "Options-> Edit->Paste the letters for the current game: " << endl;
	for (int i = 0; i < grid_length*grid_width; i++)
	{
		cin >> letter;
		grid_letters.push_back(letter);
	}

	cout << endl;
	cout << "Current Letter Grid: " << endl;
	for (int y = 0; y < grid_length; ++y)
	{
		for (int x = 0; x < grid_width; ++x)
		{
			grid[x][y] = grid_letters[y*grid_width + x];
			cout << grid[x][y] << " ";
		}
		cout << endl;
	}
	cout << endl;
}

//fancy dictionary loading progress bar
static inline void loadbar(unsigned int x, unsigned int n, unsigned int w = 50)
{
	if ((x != n) && (x % (n / 100 + 1) != 0)) return;

	float ratio = x / (float)n;
	int   c = (int)(ratio * w);

	cout << setw(3) << (int)(ratio * 100) << "% [";
	for (int x = 0; x < c; x++) cout << "=";
	for (unsigned int x = c; x < w; x++) cout << " ";
	cout << "]\r" << flush;

}

//loads the dictonary words
void load_wordlist(vector<string>&list)
{
	string line;
	ifstream file_input("wordlist.txt");
	if (file_input.is_open())
	{
		std::cout << "Loading Dictionary. " << endl;
		while (getline(file_input, line))
		{
			list.push_back(line);

			loadbar(list.size(), 1048575, 50);
		}
		file_input.close();
	}
	std::cout << endl;
	std::cout << "Finished Loading Dictionary. " << endl;
}

void find_preliminary_answers(vector<string>&list, vector<string>&temp_list, vector_2dc &grid, int grid_length, int grid_width)
{
//	bool temp_list_found;//indicates that word is part of the currently checked words list
	bool main_list_found;//word is part of the dictionary --> save to output file
	string word;
	vector<string> search;
	char letter1, letter2, letter3, letter4, letter5, letter6;

	std::cout << "finding 3-6 letter words" << endl;
	//letter 1
	for (int y = 0; y < grid_length; ++y)
	{
		for (int x = 0; x < grid_width; ++x)
		{
			//letter 2
			for (int y2 = -1; y2 <= 1; ++y2)
			{
				for (int x2 = -1; x2 <= 1; ++x2)
				{
					if ((x + x2) < 0 || (x + x2) >= grid_width || (y + y2) < 0 || (y + y2) >= grid_length ||
						&grid[x][y] == &grid[x + x2][y + y2])
					{
						continue;
					}
					//letter 3
					for (int y3 = -1; y3 <= 1; ++y3)
					{
						for (int x3 = -1; x3 <= 1; ++x3)
						{
							if ((x + x2 + x3) < 0 || (x + x2 + x3) >= grid_width || (y + y2 + y3) < 0 || (y + y2 + y3) >= grid_length ||
								&grid[x + x2][y + y2] == &grid[x + x2 + x3][y + y2 + y3] ||
								&grid[x][y] == &grid[x + x2 + x3][y + y2 + y3])
							{
								continue;
							}

							letter1 = grid[x][y];
							letter2 = grid[x + x2][y + y2];
							letter3 = grid[x + x2 + x3][y + y2 + y3]; //pick the three letters
							word += letter1;
							word += letter2;
							word += letter3;//put the letters into a word

							search.push_back(word);
							main_list_found = binarySearch(list, list.size(), search);

							if (main_list_found)
							{
								temp_list.push_back(word);
							}
							search.clear();
							word.clear();

							//letter 4
							for (int y4 = -1; y4 <= 1; ++y4)
							{
								for (int x4 = -1; x4 <= 1; ++x4)
								{
									if ((x + x2 + x3 + x4) < 0 || (x + x2 + x3 + x4) >= grid_width || (y + y2 + y3 + y4) < 0 || (y + y2 + y3 + y4) >= grid_length ||
										&grid[x][y] == &grid[x + x2 + x3 + x4][y + y2 + y3 + y4] ||
										&grid[x + x2][y + y2] == &grid[x + x2 + x3 + x4][y + y2 + y3 + y4] ||
										&grid[x + x2 + x3][y + y2 + y3] == &grid[x + x2 + x3 + x4][y + y2 + y3 + y4]
										)
									{
										continue;
									}

									//letter 5
									for (int y5 = -1; y5 <= 1; ++y5)
									{
										for (int x5 = -1; x5 <= 1; ++x5)
										{
											if ((x + x2 + x3 + x4 + x5) < 0 || (x + x2 + x3 + x4 + x5) >= grid_width || (y + y2 + y3 + y4 + y5) < 0 || (y + y2 + y3 + y4 + y5) >= grid_length ||
												&grid[x][y] == &grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5] ||
												&grid[x + x2][y + y2] == &grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5] ||
												&grid[x + x2 + x3][y + y2 + y3] == &grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5] ||
												&grid[x + x2 + x3 + x4][y + y2 + y3 + y4] == &grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5]
												)
											{
												continue;
											}

											letter1 = grid[x][y];
											letter2 = grid[x + x2][y + y2];
											letter3 = grid[x + x2 + x3][y + y2 + y3];
											letter4 = grid[x + x2 + x3 + x4][y + y2 + y3 + y4];
											letter5 = grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5];
											word += letter1;
											word += letter2;
											word += letter3;
											word += letter4;
											word += letter5;

											search.push_back(word);
											main_list_found = binarySearch(list, list.size(), search);

											if (main_list_found)
											{
												temp_list.push_back(word);
											}
											search.clear();
											word.clear();

											//letter 6
											for (int y6 = -1; y6 <= 1; ++y6)
											{
												for (int x6 = -1; x6 <= 1; ++x6)
												{
													if ((x + x2 + x3 + x4 + x5 + x6) < 0 || (x + x2 + x3 + x4 + x5 + x6) >= grid_width || (y + y2 + y3 + y4 + y5 + y6) < 0 || (y + y2 + y3 + y4 + y5 + y6) >= grid_length ||
														&grid[x][y] == &grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6] ||
														&grid[x + x2][y + y2] == &grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6] ||
														&grid[x + x2 + x3][y + y2 + y3] == &grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6] ||
														&grid[x + x2 + x3 + x4][y + y2 + y3 + y4] == &grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6] ||
														&grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5] == &grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6]
														)
													{
														continue;
													}

													letter1 = grid[x][y];
													letter2 = grid[x + x2][y + y2];
													letter3 = grid[x + x2 + x3][y + y2 + y3];
													letter4 = grid[x + x2 + x3 + x4][y + y2 + y3 + y4];
													letter5 = grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5];
													letter6 = grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6];
													word += letter1;
													word += letter2;
													word += letter3;
													word += letter4;
													word += letter5;
													word += letter6;

													search.push_back(word);
													main_list_found = binarySearch(list, list.size(), search);

													if (main_list_found)
													{
														temp_list.push_back(word);
													}
													search.clear();
													word.clear();

												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			loadbar((y*grid_length + x), 25, 50);
		}
	}
	std::cout << endl;
}

//itterates through grid to create the 3,5,6,7,8 letter words and check which ones are actual words
void find_answers(vector<string>&list, vector<string>&temp_list, vector_2dc &grid, int grid_length, int grid_width)
{
//	bool temp_list_found;//indicates that word is part of the currently checked words list
	bool main_list_found;//word is part of the dictionary --> save to output file
	string word;
	vector<string> search;
	char letter1, letter2, letter3, letter4, letter5, letter6, letter7, letter8;
	int counter = 0;

	std::cout << "finding 7-8 letter words" << endl;
	//letter 1
	for (int y = 0; y < grid_length; ++y)
	{
		for (int x = 0; x < grid_width; ++x)
		{
			//letter 2
			for (int y2 = -1; y2 <= 1; ++y2)
			{
				for (int x2 = -1; x2 <= 1; ++x2)
				{
					if ((x + x2) < 0 || (x + x2) >= grid_width || (y + y2) < 0 || (y + y2) >= grid_length ||
						&grid[x][y] == &grid[x + x2][y + y2])
					{
						continue;
					}
					//letter 3
					for (int y3 = -1; y3 <= 1; ++y3)
					{
						for (int x3 = -1; x3 <= 1; ++x3)
						{
							if ((x + x2 + x3) < 0 || (x + x2 + x3) >= grid_width || (y + y2 + y3) < 0 || (y + y2 + y3) >= grid_length ||
								&grid[x + x2][y + y2] == &grid[x + x2 + x3][y + y2 + y3] ||
								&grid[x][y] == &grid[x + x2 + x3][y + y2 + y3])
							{
								continue;
							}

							//letter 4
							for (int y4 = -1; y4 <= 1; ++y4)
							{
								for (int x4 = -1; x4 <= 1; ++x4)
								{
									if ((x + x2 + x3 + x4) < 0 || (x + x2 + x3 + x4) >= grid_width || (y + y2 + y3 + y4) < 0 || (y + y2 + y3 + y4) >= grid_length ||
										&grid[x][y] == &grid[x + x2 + x3 + x4][y + y2 + y3 + y4] ||
										&grid[x + x2][y + y2] == &grid[x + x2 + x3 + x4][y + y2 + y3 + y4] ||
										&grid[x + x2 + x3][y + y2 + y3] == &grid[x + x2 + x3 + x4][y + y2 + y3 + y4]
										)
									{
										continue;
									}

									//letter 5
									for (int y5 = -1; y5 <= 1; ++y5)
									{
										for (int x5 = -1; x5 <= 1; ++x5)
										{
											if ((x + x2 + x3 + x4 + x5) < 0 || (x + x2 + x3 + x4 + x5) >= grid_width || (y + y2 + y3 + y4 + y5) < 0 || (y + y2 + y3 + y4 + y5) >= grid_length ||
												&grid[x][y] == &grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5] ||
												&grid[x + x2][y + y2] == &grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5] ||
												&grid[x + x2 + x3][y + y2 + y3] == &grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5] ||
												&grid[x + x2 + x3 + x4][y + y2 + y3 + y4] == &grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5]
												)
											{
												continue;
											}

											//letter 6
											for (int y6 = -1; y6 <= 1; ++y6)
											{
												for (int x6 = -1; x6 <= 1; ++x6)
												{
													if ((x + x2 + x3 + x4 + x5 + x6) < 0 || (x + x2 + x3 + x4 + x5 + x6) >= grid_width || (y + y2 + y3 + y4 + y5 + y6) < 0 || (y + y2 + y3 + y4 + y5 + y6) >= grid_length ||
														&grid[x][y] == &grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6] ||
														&grid[x + x2][y + y2] == &grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6] ||
														&grid[x + x2 + x3][y + y2 + y3] == &grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6] ||
														&grid[x + x2 + x3 + x4][y + y2 + y3 + y4] == &grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6] ||
														&grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5] == &grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6]
														)
													{
														continue;
													}

													//letter 7
													for (int y7 = -1; y7 <= 1; ++y7)
													{
														for (int x7 = -1; x7 <= 1; ++x7)
														{
															if ((x + x2 + x3 + x4 + x5 + x6 + x7) < 0 || (x + x2 + x3 + x4 + x5 + x6 + x7) >= grid_width || (y + y2 + y3 + y4 + y5 + y6 + y7) < 0 || (y + y2 + y3 + y4 + y5 + y6 + y7) >= grid_length ||
																&grid[x][y] == &grid[x + x2 + x3 + x4 + x5 + x6 + x7][y + y2 + y3 + y4 + y5 + y6 + y7] ||
																&grid[x + x2][y + y2] == &grid[x + x2 + x3 + x4 + x5 + x6 + x7][y + y2 + y3 + y4 + y5 + y6 + y7] ||
																&grid[x + x2 + x3][y + y2 + y3] == &grid[x + x2 + x3 + x4 + x5 + x6 + x7][y + y2 + y3 + y4 + y5 + y6 + y7] ||
																&grid[x + x2 + x3 + x4][y + y2 + y3 + y4] == &grid[x + x2 + x3 + x4 + x5 + x6 + x7][y + y2 + y3 + y4 + y5 + y6 + y7] ||
																&grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5] == &grid[x + x2 + x3 + x4 + x5 + x6 + x7][y + y2 + y3 + y4 + y5 + y6 + y7] ||
																&grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6] == &grid[x + x2 + x3 + x4 + x5 + x6 + x7][y + y2 + y3 + y4 + y5 + y6 + y7]
																)
															{
																continue;
															}

															letter1 = grid[x][y];
															letter2 = grid[x + x2][y + y2];
															letter3 = grid[x + x2 + x3][y + y2 + y3];
															letter4 = grid[x + x2 + x3 + x4][y + y2 + y3 + y4];
															letter5 = grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5];
															letter6 = grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6];
															letter7 = grid[x + x2 + x3 + x4 + x5 + x6 + x7][y + y2 + y3 + y4 + y5 + y6 + y7];
															word += letter1;
															word += letter2;
															word += letter3;
															word += letter4;
															word += letter5;
															word += letter6;
															word += letter7;

															search.push_back(word);
															main_list_found = binarySearch(list, list.size(), search);

															if (main_list_found)
															{
																temp_list.push_back(word);
															}

															search.clear();
															word.clear();

															//letter 8
															for (int y8 = -1; y8 <= 1; ++y8)
															{
																for (int x8 = -1; x8 <= 1; ++x8)
																{
																	if ((x + x2 + x3 + x4 + x5 + x6 + x7 + x8) < 0 || (x + x2 + x3 + x4 + x5 + x6 + x7 + x8) >= grid_width || (y + y2 + y3 + y4 + y5 + y6 + y7 + y8) < 0 || (y + y2 + y3 + y4 + y5 + y6 + y7 + y8) >= grid_length ||
																		&grid[x][y] == &grid[x + x2 + x3 + x4 + x5 + x6 + x7 + x8][y + y2 + y3 + y4 + y5 + y6 + y7 + y8] ||
																		&grid[x + x2][y + y2] == &grid[x + x2 + x3 + x4 + x5 + x6 + x7 + x8][y + y2 + y3 + y4 + y5 + y6 + y7 + y8] ||
																		&grid[x + x2 + x3][y + y2 + y3] == &grid[x + x2 + x3 + x4 + x5 + x6 + x7 + x8][y + y2 + y3 + y4 + y5 + y6 + y7 + y8] ||
																		&grid[x + x2 + x3 + x4][y + y2 + y3 + y4] == &grid[x + x2 + x3 + x4 + x5 + x6 + x7 + x8][y + y2 + y3 + y4 + y5 + y6 + y7 + y8] ||
																		&grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5] == &grid[x + x2 + x3 + x4 + x5 + x6 + x7 + x8][y + y2 + y3 + y4 + y5 + y6 + y7 + y8] ||
																		&grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6] == &grid[x + x2 + x3 + x4 + x5 + x6 + x7 + x8][y + y2 + y3 + y4 + y5 + y6 + y7 + y8] ||
																		&grid[x + x2 + x3 + x4 + x5 + x6 + x7][y + y2 + y3 + y4 + y5 + y6 + y7] == &grid[x + x2 + x3 + x4 + x5 + x6 + x7 + x8][y + y2 + y3 + y4 + y5 + y6 + y7 + y8]
																		)
																	{
																		continue;
																	}

																	letter1 = grid[x][y];
																	letter2 = grid[x + x2][y + y2];
																	letter3 = grid[x + x2 + x3][y + y2 + y3];
																	letter4 = grid[x + x2 + x3 + x4][y + y2 + y3 + y4];
																	letter5 = grid[x + x2 + x3 + x4 + x5][y + y2 + y3 + y4 + y5];
																	letter6 = grid[x + x2 + x3 + x4 + x5 + x6][y + y2 + y3 + y4 + y5 + y6];
																	letter7 = grid[x + x2 + x3 + x4 + x5 + x6 + x7][y + y2 + y3 + y4 + y5 + y6 + y7];
																	letter8 = grid[x + x2 + x3 + x4 + x5 + x6 + x7 + x8][y + y2 + y3 + y4 + y5 + y6 + y7 + y8];
																	word += letter1;
																	word += letter2;
																	word += letter3;
																	word += letter4;
																	word += letter5;
																	word += letter6;
																	word += letter7;
																	word += letter8;
																	search.push_back(word);
																	main_list_found = binarySearch(list, list.size(), search);

																	if (main_list_found)
																	{
																		temp_list.push_back(word);
																	}

																	search.clear();
																	word.clear();
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			loadbar((y*grid_length + x), 25, 50);
		}
	}
	std::cout << endl;
}

//for better text file output
struct compare {
	bool operator()(const string& first, const string& second) {
		return first.size() < second.size();
	}
};

int main()
{
	// allows console output in cyrillic
	
	SetConsoleCP(GetACP());
	SetConsoleOutputCP(GetACP());
		
	vector<string> list; //dictionary list
	vector<string> temp_list; // list of current words

	int grid_height = 5;
	int grid_width = 6;
	vector_2dc grid(grid_width, vector<char>(grid_height)); //letter grid
	bool consecutive_game = false;

	//create the output file (or empty it since the last use)
	ofstream file_output;
	file_output.open("results.txt");
	file_output.close();

	load_wordlist(list);//Fill the list with the ~1 million sorted dictionary words (takes about 1.5mins)

	while (true)
	{
		char continue_game;
		while (consecutive_game)
		{
			std::cout << "Done using the results from last game? (y/n)" << endl;
			cin >> continue_game;
			if (continue_game == 'y' || continue_game == 'Y' || continue_game == 'yes' || continue_game == 'Yes')
			{
				file_output.open("results.txt", ios::trunc);
				file_output.close();
				temp_list.clear();
				break;
			}
			else if (continue_game == 'n' || continue_game == 'N' || continue_game == 'no' || continue_game == 'No')
			{
				continue;
			}
		}

		initialize_grid(grid, grid_height, grid_width);//set the letters

		find_preliminary_answers(list, temp_list, grid, grid_height, grid_width);

		compare c_prelim;
		sort(temp_list.begin(), temp_list.end());
		temp_list.erase(unique(temp_list.begin(), temp_list.end()), temp_list.end());//remove repeating found words
		sort(temp_list.begin(), temp_list.end(), c_prelim);

		file_output.open("results.txt", ios::app);
		for (auto it = temp_list.begin(); it != temp_list.end(); it++) {
			file_output << *it << endl; //output unique words from current itteration
		}
		file_output.close();

		find_answers(list, temp_list, grid, grid_height, grid_width);//itterate through grid and find words (takes <1min)

		compare c;
		sort(temp_list.begin(), temp_list.end());
		temp_list.erase(unique(temp_list.begin(), temp_list.end()), temp_list.end());//remove repeating found words
		sort(temp_list.begin(), temp_list.end(), c);

		file_output.open("results.txt", ios::trunc);
		for (auto it = temp_list.begin(); it != temp_list.end(); it++) {
			file_output << *it << endl; //output unique words from current itteration
		}
		file_output.close();

		std::cout << "Do you want to play again? (y/n)" << endl;
		cin >> continue_game;
		if (continue_game == 'y' || continue_game == 'Y' || continue_game == 'yes' || continue_game == 'Yes'){
			consecutive_game = true;
			continue;
		}
		else if (continue_game == 'n' || continue_game == 'N' || continue_game == 'no' || continue_game == 'No'){
			std::cout << "Thanks for playing." << endl;
			break;
		}
	}

	system("pause");

	return 0;
}