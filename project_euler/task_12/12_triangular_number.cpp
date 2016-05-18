#include <iostream>



using namespace std;

main()
{
    int n=0;//itterates the triangular number
    long long int  triang_num=0;
    int max_div=0; //max number of divisors
    int div=0;//current number of divisors


 while (max_div<=500){
	n++;
	triang_num=triang_num+n;
	cout<<"current triang_num: "<<triang_num<<"and max div:"<<max_div<<endl;

//	cout<<"divisors of "<<triang_num<<" are: ";
        
	long long int half_triang_num=floor(triang_num/2);

	for (long long int i=1; i<=half_triang_num; i++){
	    if(triang_num%i==0){
		div++;
//		cout<<i<<" ,";
	    }
	    if (div>max_div){
		max_div=div;
	    }

	}
    int cur_max_div;
    
    if (max_div>cur_max_div){
    cur_max_div=max_div;
    cout<<"max divisors for "<<triang_num<<" are: "<<max_div<<endl;
    }
	div=0;
//	cout<<endl;
    }

    cout<<"max divisors for "<<triang_num<<" are: "<<max_div<<endl;

    return 0;
}
