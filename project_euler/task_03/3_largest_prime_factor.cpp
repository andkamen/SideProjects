#include <iostream>
#include <cmath>

using namespace std;

bool prime(long long int);


int main()
{
    long long int  number=600851475143; //number to be tested
    long long int half_number=floor(number/2); 
    long long int prime_max=0;// largest prime diviser of the tested number
    for (long long int i=3; i<half_number;i=i+2){

        if(i%100000001==0){
	    cout<<"current i is:  "<<i<<endl;
	    cout<<"goal is     :  300000000000"<<endl;
            cout<<"current largest prime divisor is: "<<prime_max<<endl;
	}


	if(number%i==0) {
	    if(prime(i)==true){
                prime_max=i;   
	    }
	}
    }

    if(prime_max==0){
       cout<<"Number has no prime divisors"<<endl;
    }
    else{ 
       cout<<"largest prime divisor is: "<<prime_max<<endl;
    }

    return 0;
}



bool prime(long long int number)
{
    //take half the number to check if its prime. Save computational time
    long long int half_number=floor(number/2); 
    //check if its even

    if(number%2==0) {
	return false;	
    }
    //start checking every odd number. (if its even then its not prime)

    for (long long int i=3; i<half_number;i=i+2){
	if(number%i==0) {
	    return false;	
	}

    }
    return true;
}
