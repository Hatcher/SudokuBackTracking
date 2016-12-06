//============================================================================
// Name        : SudokuBackTracking.cpp
// Author      : Anderson Thomas
// Version     :
// Copyright   :
// Description : C++, Ansi-style
//============================================================================

#include <iostream>
#include <vector>
#include <string>
using namespace std;

/*
* We need to specify the constraints on our input.
* 1) No repeated values in row
* 2) No repeated values in column
* 3) If inputBoard[r] != '.', X[r] must == inputBoard[r]
* 4) No repeated values in submatrix, need to find relation for submatrix.
*/
bool bound(vector<int> X, int r, vector<vector<char > > inputBoard){
    /*
    * Use r - remainder to r, check if duplicate values.
    */
    int remainderOfR = r%inputBoard.size();
    int startOfRow = r-remainderOfR;
    int inputBoardRowIndex = 0; //What index value would the inputBoard have?
    for(int i = startOfRow; i < r; i++){
        inputBoardRowIndex++;
        if(X[r] == X[i]){
        	cout << "first constraint " << endl;
            return false;
        }
    }
    /*
    * Use column indexing, return false if duplicate.
    * Starting from remainder increase by size of a row up to r.
    */
    int inputBoardColumnIndex = 0;
    for(int i = remainderOfR; i < r; i+inputBoard.size()){
        inputBoardColumnIndex++;
        if(X[r] == X[i]){
        	cout << "second constraint " << endl;
            return false;
        }
    }
    /*
    * Test if value can be manipulated.
    * Given the row and column, is the value stored in inputBoard preset?
    * If it is, the value of X[r] must equal inputBoard[i][j].
    */
    cout << " inpurtBoardRowIndex " << inputBoardRowIndex << endl;
    inputBoardRowIndex--;
    vector<char> row = inputBoard[inputBoardRowIndex];
    for(int i = 0; i < 9; i++){
    	cout << row[i] << " | ";
    }
    cout << endl;
    //cout << row.size() << " is the row size" << endl;
    char entryOpen = '.';
    if(row[inputBoardColumnIndex] != entryOpen){
    	/*
    	 *  Entry being found is not correct when the first entry of the row != '.'
    	 *
    	 */
        int inputBoardEntry = row[inputBoardColumnIndex];
        cout << inputBoardEntry << " is the entry" << endl;
        if(inputBoardEntry != X[r]){
        	cout << "third constraint " << endl;
            return false;
        }
    }

    /*
    * Index within the subgraph, test for duplicates.
    * The start of the subgraph is r-(9*((r/9)%3))-(r%3).
    * Could expand the size of the sudoko board if we used sqrt(inputBoard.size()) instead of 3.
    */
    int startOfSubGraph = r-(inputBoard.size()*((r/inputBoard.size())%3))-(r%3);
    //Working from the start of the subgraph row to the row containing r
    int count = 0;
    for(int i = startOfSubGraph; i < r; i++){
        count++;
        if(X[i] == X[r]){
        	cout << "fourth constraint " << endl;
            return false;
        }
        if(count == 3){
            count = 0;
            i = i+9;
            i = i-2;
        }
    }
    return true;
}
vector<int> getNext(vector<int> X, int r, vector<vector<char > > inputBoard){
    int entryValue = X[r];
    entryValue++;
    X[r] = entryValue;
    while(X[r] <= inputBoard.size()){
    	cout << X[r] << " x[r], but inputboard size is " << inputBoard.size() << endl;
        if(bound(X, r, inputBoard) == true){
            return X;
        }else{
        	entryValue++;
        	X[r] = entryValue;
        }
    }
    X[r] = 0; //No compliant values were found
    return X;
}
/*
* We want to use a solution tree and go through the possible solutions.
* We will represent each possible solution path in the permutationBoard
* Using constraints, a bound function, we can specify if a value in the permutationBoard is valid.
*/
void backTrack(vector<vector<char > >inputBoard){
    int treeLevel = 1;
    vector<int> permutationBoard;
    //cout << " hey there " << endl;
    /*
    * Set all entries in the permutationArray to 0
    * Already has two for loops of size n
    */
    int N = inputBoard.size()*inputBoard.size();
    permutationBoard.assign(N, 0); //Instead of using for loops
    //cout << permutationBoard.size() << endl;
    /*
    * Lets start testing values in the next position
    * permutationBoard[0:8][0:8]
    * what is index relation for subgraph?
    */
    while(treeLevel > 0){
    	cout << "tree level is " << treeLevel << endl;
        permutationBoard = getNext(permutationBoard, treeLevel, inputBoard); //Assign a value to permutationBoard[treeLevel]

        if(permutationBoard[treeLevel] == 0){ //If value assigned is starting position, lets backTrack
            treeLevel--;
        }else if(treeLevel == N){ //If treeLevel is the last position, then we found a solution.
            for(int i =0; i < inputBoard.size(); i++){
                cout << permutationBoard[i];
            }
            cout << endl;
        }else{ //If treeLevel found a value that is valid but the permutationBoard is not complete, move to next level
            treeLevel++;
            cout << permutationBoard[0] << " next level" << endl;
        }
    }
}



int main() {
	vector<vector<char > > board;
	std::string str = "..9748...";
	std::string str1 = "7........";
	std::string str2 = ".2.1.9...";
	std::string str3 = "..7...24.";
	std::string str4 = ".64.1.59.";
	std::string str5 = ".98...3..";
	std::string str6 = "...8.3.2.";
	std::string str7 = "........6";
	std::string str8 = "...2759..";

	vector<char> row;
	vector<char> row1;
	vector<char> row2;
	vector<char> row3;
	vector<char> row4;
	vector<char> row5;
	vector<char> row6;
	vector<char> row7;
	vector<char> row8;

	for(int i = 0; i < 9; i++){
		row.push_back(str.at(i));
		row1.push_back(str1.at(i));
		row2.push_back(str2.at(i));
		row3.push_back(str3.at(i));
		row4.push_back(str4.at(i));
		row5.push_back(str5.at(i));
		row6.push_back(str6.at(i));
		row7.push_back(str7.at(i));
		row8.push_back(str8.at(i));
	}
	board.push_back(row);
	board.push_back(row1);
	board.push_back(row2);
	board.push_back(row3);
	board.push_back(row4);
	board.push_back(row5);
	board.push_back(row6);
	board.push_back(row7);
	board.push_back(row8);



	/*
	* BackTracking might work to solve this. How to get around the set values of cells and init with A0 = 0?
	* X[1:N] where N = 9^2 but should be 0 indexed, so X[0:80].
	*
	*/
	backTrack(board);
	cout << " at the end " <<endl;
	return 0;
}
