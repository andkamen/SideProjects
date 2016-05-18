#include <iostream>
#include <cmath>
using namespace std;

bool prime(long);


main()
{

    long long int prime_sum=0;

    for (int i=3; i<2000000; i=i+2){
	if (prime(i)==true){
	    prime_sum=prime_sum+i;
	}
	if (i%10001==0){
	    cout<<"Code is on number: "<<i<<endl;
	}
    }
    cout<<prime_sum+2<<endl;//+2 because prime doesnt test for 2. 

    return 0;
}


bool prime(long number)
{
    long half_number=floor(number/2);
    
    if (number%5==0){
	return false;
    }


    //start checking every odd number. (if its even then its not prime)

    for (long i=3; i<half_number; i=i+2){
	if(number%i==0) return false;
    }


    return true;

}
