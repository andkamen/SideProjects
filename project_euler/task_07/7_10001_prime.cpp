#include <iostream>
#include <cmath>


using namespace std;


bool prime (long int);



int main(){

    int prime_count=1; //number of primes found so far
    long int largest_prime=0;

    for (long int current_number=3; prime_count<10001; current_number=current_number+2){
	if (prime(current_number)==true) {
	    prime_count++; 
	    largest_prime=current_number;
            cout<<largest_prime<<endl;
	    if (prime_count%10==0){
//		cout<<prime_count<<" prime numbers have been found so far"<<endl;
	    }
         }
    }

    cout<<"The "<<prime_count<<"st prime number is: "<<largest_prime<<endl;


    return 0;
}

bool prime (long int tested_num){


    long int half_num=floor(tested_num/2);
    if (tested_num%2==0){
       return false;
    }
    for (long int i=3; i<=half_num; i=i+2){

	if(tested_num%i == 0){
	    return false;
	}

    }
    return true;
}






