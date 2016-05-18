#include <iostream>
#include <string>
#include <cstring>
#include <sstream>//transfer from stream to int etc
using namespace std;



int main()
{


    int counter=0;
    string number_str;
    string digit_i;
    string digit_j;
    string digit_k;
    string digit_l;


    char string_arr[43][5];

    //those embeded for loops create all the possible combinations 
    //of numbers without repeating digits.

    for (int i=1;i<=4;i++){
	for (int j=0;j<=3;j++){
	    if (i==j) continue;
	    for (int k=0;k<=3;k++){
		if ((i==k)||(j==k)) continue;
		for (int l=0;l<=3;l++){
		    if ((i==l)||(j==l)||(k==l)) continue;
		    counter++;     

/*
		    number_str.clear();
		    digit_i.clear();
		    digit_j.clear();
		    digit_k.clear();
		    digit_l.clear();

		    string digit_i=static_cast<ostringstream*>(&(ostringstream()<<i))->str();
		    number_str.append(digit_i);

		    string digit_j=static_cast<ostringstream*>(&(ostringstream()<<j))->str();
		    number_str.append(digit_j);

		    string digit_k=static_cast<ostringstream*>(&(ostringstream()<<k))->str();
		    number_str.append(digit_k);

		    string digit_l=static_cast<ostringstream*>(&(ostringstream()<<l))->str();
		    number_str.append(digit_l);

		    cout<<"number is: "<<number_str<<endl;

                    strcpy(string_arr[counter],number_str);
*/
                    string_arr[0][0]=i;

		}
	    }
	}
    }


    cout<<"counter: "<<counter<<endl;


cout<<string_arr[0][0]<<endl;



    return 0;
}
