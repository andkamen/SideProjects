#include <iostream>

using namespace std;
//Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.

int main()
{
    int sum=0, square=0, difference;


    for (int i=1; i<=100; i++){
	sum+=i*i;
    }

    for (int j=1; j<=100; j++){
	square+=j;
    }
    square=square*square;

    difference=square-sum;

    cout<<"difference is "<<difference<<endl;

    return 0;
}
