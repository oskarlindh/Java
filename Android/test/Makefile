#Directories
SRC = src
BUILD_DIR = build 

#Add JAR files for testing to classpath
CLASSPATH = build

#Classes for running
MAIN_CLASS = Main 

#Flags  
DX_FLAGS = --dex --output $(BUILD_DIR)/classes.dex 

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
	$(DALVIKVM) -cp $(CLASSPATH)/classes.dex  	

clean :
	rm -rf $(BUILD_DIR) ./*.txt $(DOCDIR)

clean-build:
	rm -rf $(BUILD_DIR)

debug:
	jdb -sourcepath $(SRC) $(RESOURCES)/*.java


