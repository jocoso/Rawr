#include "../../customLibrary/src/Kraken.h"

int main(void) {
	printf("\n\n");
	string cmd = "";
	while(1) {
		std::cout << "Enter command in DOS:";
		std::getline(std::cin, cmd);
		pipecommand(cmd);
		saySomething(cmd);
	}
	printf("\n\n");

	return 0;
}