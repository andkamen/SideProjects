#include <iostream>
using namespace std;

//This program calculates the sum of even fibonachi numbers up to 4mil

int main()
{
    //declare variables
    int n=0, a_n;
    int sum=0;
    int a_n_minus1=1;
    int a_n_minus2=1;

    //calculate value of a_n and assign new values for an-1/an-2 and loop untill i=n 
    /*
    because chances of some nth fib number to be 4mil is small, the while loop will itterate once more and will calculate the value of a_n higher then 4mil as well. 
    thats why a_n_minus1 is used as it gives the previous value, the 100% less then 4mil one. 
    */
    while(a_n<=4000000){   

	a_n=(a_n_minus1 + a_n_minus2);
	//check and sum the even fib numbers. Discard other values.
	if (a_n_minus1%2==0){
	    sum+=a_n_minus1; 
	}
	a_n_minus2=a_n_minus1;
	a_n_minus1=a_n;
    }  
    //print result

    cout<<"sum of even fibonachi is: "<<sum<<endl;


    return 0;
}

