#include <iostream>
#include <cmath>
using namespace std;


main(){

    for (int a=1; a<1000; a++){
	for (int b=2; b<1000; b++){
	    for(int c=3; c<1000; c++){

		if (a>=b||b>=c) continue;

		if (a+b+c!=1000) continue;

		if (a*a+b*b==c*c){

		    cout<<a<<" "<<b<<" "<<c<<endl;
		    cout<<"The product of abc is: "<<a*b*c<<endl;
		}



	    }
	}
    }



    return 0;
}
