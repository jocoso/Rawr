APP_NAME = Kraken
BUILD_DIR = ./bin

FLAGS_DLL:= -DBUILD_DLL

LIB_SRC:= ./customLibrary/src
CLIENT_SRC:= ./ClientApp/src
OBJ_DIR:= $(BUILD_DIR)/obj

all: createSO buildClient

createSO:
	g++ -c $(FLAGS_DLL) $(LIB_SRC)/Kraken.cpp -o $(OBJ_DIR)/Kraken.o
	g++ -shared -o $(BUILD_DIR)/Kraken.dll $(OBJ_DIR)/Kraken.o -Wl,--out-implib,$(BUILD_DIR)/krakenlib.a

buildClient:
	g++ -c $(CLIENT_SRC)/main.cpp -o $(OBJ_DIR)/main.o 
	g++ -o $(BUILD_DIR)/client.exe $(OBJ_DIR)/main.o -L. -l:$(BUILD_DIR)/krakenlib.a

clean:
	rm -r $(OBJ_DIR)/*.* $(BUILD_DIR)/client.exe $(BUILD_DIR)/Kraken.dll $(BUILD_DIR)/*.a
