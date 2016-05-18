#include <iostream>
using namespace std;


main (){

    char arrayNum[]="7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450";

    // gives size of array by dividing size(bytes) of total array by size of a single element in the array. -1 for the /o of the array that we dont care about 
    int arraySize=sizeof(arrayNum)/sizeof(arrayNum[0])- 1; 
    cout<<"string size: "<<arraySize<<endl;
    int product=1;
    int product_max=0;
    int multiplier=0;
    int product_length=5;
    int product_series[product_length], max_product_series[product_length];


	//itterates from first element to last element that still allows for a product of x consecutive numbers
	for (int i=0; i<= arraySize-product_length; i++){
	    //does the product for each consecutive number
	    for (int y=0;y<product_length; y++ ){
		multiplier=arrayNum[i+y]-'0'; // The "-" operator on char type returns a distance between characters. Since Distance from char '3' to the char '0' is 3 it is used it to convert char to integer. 
		product=product*multiplier;
		product_series[y]=arrayNum[i+y];
	    }

	    if (product>product_max){
		product_max=product;

		for(int a=0; a<product_length; a++){
		    max_product_series[a]=product_series[a];
		    cout<<product_series[a]-'0';
		   }
		   cout<<endl;

	    }


	    product=1; //reset product value after each use

	}

    cout<<"largest product of "<<product_length<<" consecutive numbers is: "<<product_max<<endl;
    cout<<"the consecutive numbers are: ";

    for (int a=0;a<product_length; a++){
	cout<<max_product_series[a]-'0';
    }

    cout<<endl;
    return 0;

}


