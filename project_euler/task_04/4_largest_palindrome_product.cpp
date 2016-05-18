#include <iostream>//basic input output
#include <cstring>//string operations
#include <sstream>//assigning int to string
#include <algorithm>//using reverse function for strings
#include <fstream>//input output to files
using namespace std;

//this program will find the largest palindrome result of a product
//of two 3 digit numbers

int main()
{   
    //declare variables
    int num=999;//max ceiling for the numbers
    int i,j,palindrome_count=0;//iterators
    int number; //product of two numbers
    string max_palindrome; //final result
    int palindrome_number=1;


    //iterate between all of the possibilities for products of 3digit nums
    for(i=100;i<=num;i++){
	//j=i insures that ones already checked arent rechecket. 
	//ex: 1*2   or 2*1...
	for(j=i;j<=num;j++){
	    number=i*j;
	    //one liner that makes the int number a string number
	    string number_str = static_cast<ostringstream*>( &(ostringstream() << number) )->str();

	    string compare_number_str(number_str);
	    //reverse the string
	    reverse(compare_number_str.begin(), compare_number_str.end());
	    //compare strings
	    if (compare_number_str==number_str){
		palindrome_count++;
                //make sure that the new found palindrome is actually
		//bigger then the previous
		if (palindrome_number<number){	
		    max_palindrome=number_str;
		    palindrome_number=number;
		}
	    }
	}
    }
    cout<<palindrome_count<<" were found."<<endl;
    cout<<"The largest one was "<<max_palindrome<<endl;

    return 0;
}
