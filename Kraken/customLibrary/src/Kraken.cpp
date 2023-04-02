#include "Kraken.h"

void pipecommand(string strCmd) {
	std::array<char, 80> buffer;
	FILE *pipe = popen(strCmd.c_str(), "r");

	if(!pipe) {
		std::cerr << "Cannot Open Pipe For Reading" << std::endl;
	}

	int c = 0;
	while(fgets(buffer.data(), 80, pipe) != NULL) {
		c++;
		std::cout << c << "\t" << buffer.data();
	}

	pclose(pipe);
}

void saySomething(string str) {
	std::cout << "I typed:" << str << endl;
}