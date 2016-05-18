#include <iostream>
#include <string>
#include <fstream>
using namespace std;

int main() 
{   

    /*
       input file stream will be called "file". attach a file to it and
       open it. Read the file line by line and count the number of
       characters in each one, then count number of spaces and substract them from total #.
     */
        ifstream file;
    file.open("numbers.txt");
    string line;
    //declare variables
    int char_count_with_blanks=0;
    int char_count_with_blanks_no_eol=0;
    int number_of_blanks=0;
    int i;

    
    if (file.is_open()){
        
        //itterate through every line 
	while( getline(file, line)){ 
	    // cout<<line<<endl;
	    //because getline doenst count end of line char, we need 
	    //to add manually another char
	    char_count_with_blanks+=1;
	    char_count_with_blanks+=line.length();
	    char_count_with_blanks_no_eol+=line.length();
            //check every line, char by char to count number of blanks  
	    for (i=0; i<line.length(); i++){

		if (isspace(line[i])){
		    number_of_blanks++;
		}
	    }
	}
    }
    //close file
    file.close();
    //because in the last line end of file isnt considered a char, but we
    //added one in the loop, now we remove one char.
    cout<<"Total number of characters in file is "<<char_count_with_blanks-1<<endl;
    //calculate and print out number of chars without blanks.
    cout<<"Number of characters in file without blanks is: "<<(char_count_with_blanks_no_eol-number_of_blanks)<<endl;


    return 0;
}
