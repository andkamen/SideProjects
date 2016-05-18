#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

int main() {
    std::string s = "1357924680";
    std::vector<int> ints;
    ints.reserve(s.size());

    std::transform(std::begin(s), std::end(s), std::back_inserter(ints),
	    [](char c) {
	    return c - '0';   
	    }
        );	   

    for (int i : ints) {
	std::cout << i << ' ';
    }

  return 0;  
}
