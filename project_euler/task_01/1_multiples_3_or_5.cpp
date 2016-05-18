#include <iostream>

using namespace std;



int main()
{

    int counter=0;
    int N=1000;

    for(int i=1;i<N;i++){
	if ((i%3==0)||(i%5==0)){
	    counter+=i;
	    cout<<i<<" is divisible by 3 or 5"<<endl;
	}
    }


    cout<<"Sum of numbers divisible by 3 or 5 is: "<<counter<<endl;

    return 0;
}

