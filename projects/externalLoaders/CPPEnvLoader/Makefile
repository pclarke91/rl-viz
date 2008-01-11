#
#	These makefiles require jni.h and jni_md.h
#
#	If they dont work for you
#	1) type 'locate jni.h'
#	   set JAVA_HOME to be this path (not including the jni.h on the end)
#   2) type 'locate jni_md.h'
#      set JAVA_HEADER to be this path (not includeing the jni_md.h on the end)
#

BASE_PATH=../../../system
OUT_PATH=$(BASE_PATH)/dist/SharedLibraries
INCLUDE_PATH=$(BASE_PATH)/includes
#Needed?
GLUE_PATH=../../rl-glue/RL-Glue
ENV_HEADERS=$(BASE_PATH)/dist
#We should use PWD to get this path
GET_BACK=../../../rl-viz/projects/externalLoaders/CPPEnvLoader

#This is a Mac default
ifndef JAVA_HOME
	JAVA_HOME=/Library/Java/Home/include
endif

ifndef JAVA_HEADER
	JAVA_HEADER = $(JAVA_HOME)
endif
# supported substrings of OSTYPE
# The following words must be uppercase
# If they are found in the uppercased OSTYPE variable
# the particular O/S matches
OSTYPE_LINUX  := LINUX
OSTYPE_MAC    := DARWIN
OSTYPE_CYGWIN := CYGWIN
OSTYPE_MINGW  := MSYS

# convert OSTYPE to uppercase
ifeq ("$(OSTYPE)", "") 
	OSTYPE := $(shell uname | grep -i -E "(Darwin)|(Linux)|(CYGWIN)")
	OSTYPE := $(shell echo "$(OSTYPE)" | tr a-z A-Z )
else
	OSTYPE := $(shell echo -n "$(OSTYPE)" | tr a-z A-Z )
endif

CC=g++

LIB_HEADERS=environmentShell_LocalCPlusPlusEnvironmentLoader.h environmentShell_JNIEnvironment.h

ifeq ("$(OSTYPE)","$(OSTYPE_MAC)")
	SFLAGS := -dynamiclib 
else 
	SFLAGS := -shared
#This is for linux
endif

CPPENV.dylib: $(LIB_HEADERS)
	$(CC) -o $(OUT_PATH)/CPPENV.dylib $(SFLAGS) -fPIC -I $(JAVA_HOME) -I $(JAVA_HEADER) -I $(INCLUDE_PATH) -I$(GLUE_PATH) src/CPlusPlusEnvironmentLoader.cpp $(INCLUDE_PATH)/ParameterHolder.cpp
	cd $(ENV_HEADERS) && rm -rf META-INF
	cd $(ENV_HEADERS) && rm -rf environmentShell
	
environmentShell_LocalCPlusPlusEnvironmentLoader.h:
	cd $(ENV_HEADERS) && unzip EnvironmentShell.jar
	cd $(ENV_HEADERS) && javah environmentShell.JNIEnvironment && mv environmentShell_JNIEnvironment.h $(GET_BACK)/src

environmentShell_JNIEnvironment.h:
	cd $(ENV_HEADERS) && javah environmentShell.LocalCPlusPlusEnvironmentLoader && mv environmentShell_LocalCPlusPlusEnvironmentLoader.h $(GET_BACK)/src

clean:
	cd $(ENV_HEADERS) && rm -rf META-INF
	cd $(ENV_HEADERS) && rm -rf environmentShell
	rm -f $(OUT_PATH)/CPPENV.dylib