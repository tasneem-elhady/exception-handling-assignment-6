## Project Objective

read an ARXML file containing a list of containers, each with a unique ID, and
reorders the containers alphabetically by their "SHORT-NAME". Then write the reordered containers to a new ARXML file.

## Implemented using
Java built-in DOM parser.\
The Document Object Model (DOM) uses nodes to represent the HTML or XML document as a tree structure.\
DOM common terms using reflected on this example :
```
<AUTOSAR>
 <CONTAINER UUID="198ae269-8478-44bd-92b5-14982c4ff68a">
 <SHORT-NAME>ContainerB</SHORT-NAME>
 <LONG-NAME>AA</LONG-NAME>
 </CONTAINER>
</AUTOSAR>
```
The ```<AUTOSAR> ```is the root element.\
The ```<CONTAINER>```, ```<SHORT-NAME>``` and all <?> are the element nodes.\
The text node is the value wrapped by the element nodes; for example, ``` <SHORT-NAME>ContainerB</SHORT-NAME>```, the ContainerB is the text node.\
The attribute is part of the element node; for example,``` <CONTAINER UUID="198ae269-8478-44bd-92b5-14982c4ff68a"> ```the UUID is the attribute of the CONTAINER element.

## Exceptions
If the file is not having .arxml extension it trigger a user defined checked
exception “NotVaildAutosarFileException”.\
If the file is empty, then it  trigger user defined unchecked exception
“EmptyAutosarFileException”.

## Brief explanation
1. give an arxml file path to the input.
2. pass the file path to the readFile method of the ArxmlFile class.
3. check if its an arxml file , check if its an empty file so it throws the defined exceptions.
4. parse the file and create an object for each each of the containers with the data fields (UUID, SHORT_NAME, LONG_NAME).
5. create an array list of the containers in the file (data field of the ArxmlFile class named fileContent).
6. sort this array list.
7. Create a Document doc, XML elements, attributes, etc., and append the to the Document doc.
8. create a new arxml file named as input file concatenated with “_mod.arxml”
9. Create a Transformer to write the Document doc to the created file.
