#Directories
SRC = src
BUILD_DIR = build 
BIN = bin

CLASSPATH = build:$(BIN)/classes.dex

#Classes for running
MAIN_CLASS = HelloWorld 

#Flags  
DX_FLAGS = --no-strict --dex --output $(BIN)/classes.dex build/*.class
#Compiler and others
ECJ = ecj
DX = dx 
DALVIKVM = dalvikvm
#Comands
all: build

build: clean-build
	@mkdir $(BUILD_DIR) 
	$(ECJ) -d $(BUILD_DIR) -cp $(CLASSPATH) -sourcepath $(SRC) $(SRC)/*
	$(DX) $(DX_FLAGS)   	

run:
	$(DALVIKVM) -cp $(CLASSPATH) $(MAIN_CLASS)	

clean :
	rm -rf $(BUILD_DIR)  $(DOCDIR) $(BIN)/*

clean-build:
	rm -rf $(BUILD_DIR)

debug:
	jdb -sourcepath $(SRC) $(RESOURCES)/*.java


