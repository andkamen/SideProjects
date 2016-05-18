#include <iostream>
#include <stdio.h>
#include <string>
#include <sstream>
#include <fstream>

using namespace std;

string print_digit(string);
string print_decimal(string);
string print_teen(string digit);

int main()
{
    int number=0;
    string name;
    string full_number;
    string first, second, third, fourth;

    /*
       cout<<"Enter number to be printed out: ";
       while(1){
       cin>>number;
       if (number>1000 || number<1 ){
       cout<<"You have chosen an invalid number."<<endl;
       cout<<"Choose again: ";
       continue;
       }
       else break;
       }
     */
    
    ofstream myfile;
    myfile.open("simpson.txt");
    myfile.close();


    for(int i=1;i<1001;++i){

	number=i;
        cout<<"I is: "<<i<<" "<<"number is: "<<number;
	//  cout<<"You have chosen the number: "<<number<<endl;
	string digit = static_cast<ostringstream*>( &(ostringstream() << number) )->str();
        
	if ( digit.length()==4) full_number=digit;
	else if ( digit.length()==3) full_number.append("0"+digit);  
	else if ( digit.length()==2) full_number.append("00"+digit);  
	else if ( digit.length()==1) full_number.append("000"+digit);  


//	cout<<"full number: "<<full_number<<endl;

	first=full_number[0];
	second=full_number[1];
	third=full_number[2];
	fourth=full_number[3];
	 cout<<"first "<<first<<endl;
	 cout<<"second "<<second<<endl;
	 cout<<"third "<<third<<endl;
	 cout<<"fourth "<<fourth<<endl;

        name=" ";
	if (second!="0"&& third=="0"&& fourth=="0")
	    name.append(print_digit(second)+" hundred");

	else  ((second=="0"&& third=="0"&& fourth!="0")||(second=="0"&& third!="0"&& fourth=="0")||(second=="0"&& third!="0"&& fourth!="0"));
	name.append(print_digit(second)+" hundred and ");

	if (third=="1") 
	    name.append(print_teen(fourth));
	else if (third!="0"&& third!="1") 
	    name.append(print_decimal(third));

	if (third!="0" && third!="1")
	    cout<<"not 0 or 1"<<endl;
	name.append(print_digit(fourth));

	if (first=="1")
	    name="one thousand"; 
	cout<<"final name: "<<name<<endl;
  
    ofstream myfile;
    myfile.open("simpson.txt",std::fstream::app);
    myfile<<number<<": "<<name<<endl;
    
    myfile.close();


  
  
  
    }
    return 0;
}

string print_digit(string digit)
{
    string word;
    if (digit=="1") word="one";
    else if (digit=="2") word="two";
    else if (digit=="3") word="three";
    else if (digit=="4") word="four";
    else if (digit=="5") word="five";
    else if (digit=="6") word="six";
    else if (digit=="7") word="seven";
    else if (digit=="8") word="eight";
    else if (digit=="9") word="nine";

    return word;
}

string print_teen(string digit)
{
    string word;
    if (digit=="0") word="ten ";
    else if (digit=="1") word="eleven";
    else if (digit=="2") word="twelve";
    else if (digit=="3") word="thirteen";
    else if (digit=="4") word="fourteen";
    else if (digit=="5") word="fifteen";
    else if (digit=="6") word="sixteen";
    else if (digit=="7") word="seventeen";
    else if (digit=="8") word="eighteen";
    else if (digit=="9") word="nineteen";

    return word;
}



string print_decimal(string digit)
{
    string word;
    if (digit=="2") word="twenty ";
    else if (digit=="3") word="thirty ";
    else if (digit=="4") word="fourty ";
    else if (digit=="5") word="fifty ";
    else if (digit=="6") word="sixty ";
    else if (digit=="7") word="seventy ";
    else if (digit=="8") word="eighty ";
    else if (digit=="9") word="ninety ";

    return word;
}

