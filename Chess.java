// Variables Used: CLASS MAIN: check(checks values to exit loops), whitecheck( checks check for uppercase player),
// blackcheck(same as whitecheck but for lowercase), boolean check (checks all possible places for pieces to go),input
// This program will allow the user play chess. This game utilizes 3 boards in which one board is based on string-manipulation detection
// for the pieces to chosen and move round the String array board. The other 2 boards are boolean based which sole purpose is to check
// for if the white king and black king is in check annd/or where it can move.
import java.io.*;
class Chess
{
    public static void main (String args[])
	throws java.io.IOException
    {
	boolean check, Whitecheck = false;
	ChessMethods cm = new ChessMethods ();
	BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
	String input, pw, man = "-_-";
	boolean move;
	boolean Whitecheckmate = false, Blackcheckmate = false;
	FileWriter fw = new FileWriter ("Chess_Password.txt"); //This will write a password into a file
	do
	{
	    System.out.println ("Please set a Password\r(Requirements: Between 6-12 Characters,Uppercase, Lowercase, number, no space)");
	    pw = br.readLine ();
	    check = cm.passswordlengthcheck (pw);
	    if (check == true)
	    {
		check = cm.PasswordUpperCaseCheck (pw);
		if (check == true)
		{
		    check = cm.PasswordLowerCaseCheck (pw);
		    if (check == true)
		    {
			check = cm.PasswordNumberCheck (pw);
			if (check == true)
			{
			    check = cm.PasswordWhiteSpaceCheck (pw);
			    if (check == false)
			    {
				check = cm.SpecialCharacterCheck (pw);
			    }
			}
		    }
		}
	    }
	}
	while (check == false);
	fw.write (pw + "\r\t");
	fw.close ();
	System.out.println ("Success!");
	cm.ClrScreenmethod ();
	FileReader fr = new FileReader ("Chess_Password.txt");
	BufferedReader bfr = new BufferedReader (fr); //Read the file and retrive password
	pw = bfr.readLine ();
	System.out.println ("Please enter the password");
	do
	{
	    input = br.readLine ();
	    if (!input.equals (pw)) //Comparison between file written password and user's password
	    {
		System.out.println ("Inncorrect Password!\rPlease try again!");
	    }
	    else
	    {
		System.out.println ("Correct Password!");
	    }

	}
	while (!input.equals (pw));
	fr.close ();
	cm.ClrScreenmethod ();
	cm.emptyboard ();
	cm.setbooleanboardfree ();
	do
	{
	    System.out.println ("Please enter the desire task\r(1) - Play\r(2) - How to Play"); //Main Menu
	    input = br.readLine ();
	    if (input.equals ("1")) //Exits Loop To Start Game
	    {
		break;
	    }
	    else if (input.equals ("2")) //Instructions
	    {
		cm.ClrScreenmethod ();
		System.out.println ("General Rules:\rPawns(P_ or p_)\r-Move one square forwards. \r-You have the option of moving one or two squares on their first move.\r-Capture by moving one square diagonally forwards.\r\rKnights(K_ or k_)\r-Move in an L-shape\r-One square vertically and two squares horizontally or one square horizontally and two squares vertically.\r\rBishops(B_ or b_)\r-Move any number of squares diagonally in a straight line.\rMay not jump over other pieces.\r Please first choose the chess piece you would like to use followed with the\r\rRooks(R_ or r_)\r-Move any number of squares vertically or horizontally in a straight line.\r-May not jump over other pieces.\r\rQueens(Q_ or q_)\r-Moves any number of squares vertically, horizontally, or diagonally\r-May not jump over other pieces.\r\rKings(K_or k_)\r-Moves one square in any direction.\r-May not move onto a square threatened by an enemy piece.\r");
	    }

	}
	while (!input.equals ("1"));
	cm.ClrScreenmethod ();
	do
	{
	    do
	    {
		move = false;
		cm.showboard ();
		cm.setbooleanboard (); //BOOLEAN BOARD 1 IS ALL THE SPACES BLACK (PLAYER 2) CAN LAND ON (USED TO CHECK FOR CHECK AND CHECKMATE)
		cm.setbooleanboard2 (); //BOOLEAN BOARD 2 IS ALL THE SPACES WHITE (PLAYER 1) CAN LAND ON (USED TO CHECK FOR CHECK AND CHECKMATE)
		boolean Whitestalemate = cm.Whitestalemate (); //CHECKS FOR STALEMATE
		if (Whitestalemate == true && man.equals ("STALEMATE"))
		{
		    System.out.println ("TIE GAME");
		}
		Whitecheck = cm.WhiteKingCheck (); // Checks if White King is in check
		if (Whitecheck == true)
		{
		    Whitecheckmate = cm.WhiteCheckmate1 (); //Checks if White King is in checkmate
		    if (Whitecheckmate == true)
		    {
			System.out.println ("PLAYER 2 Wins!"); //Ends Game With Player 2 Winning
			break;
		    }
		    System.out.println ("PLAYER 1, YOU ARE IN CHECK. PLEASE MOVE YOUR KING");
		}
		if (Whitecheck == true) // Force Player to Move King if in Check
		{
		    input = "K1";
		}
		else
		{
		    System.out.println ("Which piece would you like to move (Uppercase)."); //Otherwise Player can play normally
		    input = br.readLine ();
		}
		if (input.equals ("P1") || input.equals ("P2") || input.equals ("P3") || input.equals ("P4") || input.equals ("P5") || input.equals ("P6") || input.equals ("P7") || input.equals ("P8"))
		{
		    move = cm.pawnmovement (input); //Pawm Method Called
		    if (move == true)
		    {

			break;
		    }
		}
		else if ((input.equals ("N1")) || (input.equals ("N2")))
		{
		    move = cm.Knight (input); //Knight Movement Called
		    if (move == true)
		    {

			break;
		    }
		}
		else if ((input.equals ("R1")) || (input.equals ("R2")))
		{
		    move = cm.Rook (input); //Rook Movement Called
		    if (move == true)
		    {

			break;
		    }
		}
		else if (input.equals ("K1"))
		{
		    move = cm.King (input); //King movement Called
		    if (move == true)
		    {

			break;
		    }
		}
		else if (input.equals ("Q1"))
		{

		    move = cm.Queen (input); // Queen movement Called
		    System.out.println (move);
		    if (move == true)
		    {

			break;
		    }
		}
		else if (input.equals ("B1") || input.equals ("B2"))
		{
		    move = cm.Bishopmovement (input); // Bishop Movement Called

		    if (move == true)
		    {

			break;
		    }
		}
	    }
	    while (!man.equals ("123"));
	    cm.setbooleanboard ();
	    cm.setbooleanboard2 ();
	    //--------------------------------------------------------------------------------2nd player
	    do
	    {
		if (Whitecheckmate == true)
		{
		    break;
		}
		cm.setbooleanboard2 ();
		cm.setbooleanboard ();
		cm.showboard ();
		boolean Blackstalemate = cm.Blackstalemate ();
		if (Blackstalemate == true && man.equals ("STALEMATE"))
		{
		    System.out.println ("TIE GAME");
		}
		boolean Blackcheck = cm.BlackKingCheck (); // Checks if Black King is in check
		if (Blackcheck == true)
		{
		    Blackcheckmate = cm.BlackCheckmate1 (); //
		    if (Blackcheckmate == true)
		    {
			System.out.println ("Player 1 Wins!"); //Ends Game With Player 1 Winning
			break;
		    }
		    System.out.println ("PLAYER 2, YOU ARE IN CHECK. PLEASE MOVE YOUR KING");
		}
		cm.setbooleanboardfree ();
		if (Blackcheck == true)
		{
		    input = "k1";

		}
		else if (Blackcheck == false)
		{
		    System.out.println ("Which piece would you like to move (Lowercase).");
		    input = br.readLine ();
		}
		if (input.equals ("p1") || input.equals ("p2") || input.equals ("p3") || input.equals ("p4") || input.equals ("p5") || input.equals ("p6") || input.equals ("p7") || input.equals ("p8"))
		{
		    move = cm.pawnmovement2 (input);
		    if (move == true)
		    {

			break;
		    }
		}
		else if ((input.equals ("n1")) || (input.equals ("n2")))
		{
		    move = cm.Knight2 (input);
		    if (move == true)
		    {

			break;
		    }
		}
		else if ((input.equals ("r1")) || (input.equals ("r2")))
		{
		    move = cm.Rook2 (input);
		    if (move == true)
		    {

			break;
		    }
		}
		else if (input.equals ("k1"))
		{
		    cm.setbooleanboard ();
		    cm.setbooleanboard2 ();

		    move = cm.King2 (input);
		    if (move == true)
		    {
			cm.setbooleanboard ();
			cm.setbooleanboard2 ();

			break;
		    }
		}
		else if (input.equals ("q1"))
		{
		    move = cm.Queen2 (input);
		    if (move == true)
		    {

			break;
		    }
		}
		else if (input.equals ("b1") || input.equals ("b2"))
		{
		    move = cm.Bishopmovement2 (input);
		    if (move == true)
		    {

			break;
		    }
		}
		cm.setbooleanboard2 ();
		cm.setbooleanboard ();

	    }
	    while (!input.equals ("dsadas"));
	    cm.setbooleanboard ();
	    cm.setbooleanboard2 ();
	    if (Blackcheckmate == true)
	    {
		break;
	    }
	    if (Whitecheckmate == true)
	    {
		break;
	    }
	}
	while (!input.equals ("dsadas"));
    }
}


class ChessMethods
{
    // Variables Used: CLASS METHODS: Check_board2  &Check_board(for each player, checks the boolean of each spot in the 2d array to see if the king
    // is able to move in that position, showboard(this void displays the board to the user), String board [][] (the main game board), p1-p8check
    //P1-P8check(these variables check whether or not the pawn is still able to move 2 positions up the board(chess rule)), ogrow and ogcol ( This is
    // used to save the original row and column of a certain piece which is mainly used when verifying a certain move the player wants to do. row&col(these
    // are the row and column the user chooses. Both values have 1 subtracted from it so it can adjust with the 2d array(since arrays start from 0)),x&y
    // (this is another variable to hold column and row which was used for bishop), The voids BlackCheckmate and WhiteCheckmate( these) utilize the boolean
    // arrays to know whether or not the kings for both players are in checkmate or not, if one player is, the game will end and the opposing player will
    //win).

    BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
    String board[] [] = new String [8] [8], man = "_-_";
    boolean check_board[] [] = new boolean [8] [8]; //PLAYER 1
    boolean check_board2[] [] = new boolean [8] [8]; //PLAYER 2
    boolean Blackcheck, Whitecheck, move = false;
    int P1check = 0, P2check = 0, P3check = 0, P4check = 0, P5check = 0, P6check = 0, P7check = 0, P8check = 0; //PLAYER 1
    int p1check = 0, p2check = 0, p3check = 0, p4check = 0, p5check = 0, p6check = 0, p7check = 0, p8check = 0; //PLAYER 2
    int ogrow, ogcol, row, col;
    int breaker;
    int x, y;
    boolean checker, Blackcheckmate, Whitecheckmate, breaking = false, Blackstalemate, Whitestalemate;

    boolean Blackstalemate ()
    {
	Blackstalemate = false;
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals ("k1"))
		{
		    row = num;
		    col = num1;
		    break;
		}
	    }
	}
	int num1 = col;
	int num = row;
	if (man.equals ("STALEMATE"))
	{
	    if ((check_board [num] [num1] = false) && (check_board [num - 1] [num1] = true) && (check_board [num - 1] [num1 - 1] = true) && (check_board [num - 1] [num1 + 1] = true) && (check_board [num] [num1 - 1] = true) && (check_board [num] [num1 + 1] = true) && (check_board [num + 1] [num1] = true) && (check_board [num + 1] [num1 - 1] = true) && (check_board [num + 1] [num1 + 1] = true))
	    {
		Blackstalemate = true;
	    }
	}
	return Blackstalemate;
    }


    boolean Whitestalemate ()
    {
	Whitestalemate = false;
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals ("k1"))
		{
		    row = num;
		    col = num1;
		    break;
		}
	    }
	}
	int num1 = col;
	int num = row;
	if (man.equals ("STALEMATE"))
	{
	    if ((check_board2 [num] [num1] = false) && (check_board2 [num - 1] [num1] = true) && (check_board2 [num - 1] [num1 - 1] = true) && (check_board2 [num - 1] [num1 + 1] = true) && (check_board2 [num] [num1 - 1] = true) && (check_board2 [num] [num1 + 1] = true) && (check_board2 [num + 1] [num1] = true) && (check_board2 [num + 1] [num1 - 1] = true) && (check_board2 [num + 1] [num1 + 1] = true))
	    {
		Whitestalemate = true;

	    }
	}
	return Whitestalemate;
    }


    boolean WhiteCheckmate1 ()
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals ("K1"))
		{
		    row = num;
		    col = num1;
		    break;
		}
	    }
	}


	int num1 = col;
	int num = row;
	if ((num >= 1 && num <= 6) && (num1 >= 1 && num1 <= 6))  //----------------------------CHECKS SURROUNDINGS FOR WHITE KING(LOWERCASE)
	{
	    if ((check_board2 [num - 1] [num1] == true) && (check_board2 [num - 1] [num1 - 1] == true) && (check_board2 [num - 1] [num1 + 1] == true) && (check_board2 [num] [num1 - 1] == true) && (check_board2 [num] [num1 + 1] == true) && (check_board2 [num + 1] [num1] == true) && (check_board2 [num + 1] [num1 - 1] == true) && (check_board2 [num + 1] [num1 + 1] == true))
	    {
		Whitecheckmate = true;
	    }
	}


	else if (num == 0)
	{
	    if ((check_board2 [num] [num1 - 1] == true) && (check_board2 [num] [num1 + 1] == true) && (check_board2 [num + 1] [num1] == true) && (check_board2 [num + 1] [num1 - 1] == true) && (check_board2 [num + 1] [num1 + 1] == true))
	    {
		Whitecheckmate = true;
	    }
	}


	else if (num == 7)
	{
	    if ((check_board2 [num - 1] [num1] == true) && (check_board2 [num - 1] [num1 - 1] == true) && (check_board2 [num - 1] [num1 + 1] == true) && (check_board2 [num] [num1 - 1] == true) && (check_board2 [num] [num1 + 1] == true))
	    {
		Whitecheckmate = true;
	    }
	}


	else if (num1 == 0)
	{
	    if ((check_board2 [num - 1] [num1] == true) && (check_board2 [num - 1] [num1 + 1] == true) && (check_board2 [num] [num1 + 1] == true) && (check_board2 [num + 1] [num1] == true) && (check_board2 [num + 1] [num1 + 1] == true))
	    {
		Whitecheckmate = true;
	    }
	}


	else if (num1 == 7)
	{
	    if ((check_board2 [num - 1] [num1] == true) && (check_board2 [num - 1] [num1 - 1] == true) && (check_board2 [num] [num1 - 1] == true) && (check_board2 [num + 1] [num1] == true) && (check_board [num + 1] [num1 - 1] == true))
	    {
		Whitecheckmate = true;
	    }
	}



	if (Whitecheckmate == true)
	{
	    System.out.println ("Checkmate!");
	}


	return Whitecheckmate;
    }



    boolean BlackCheckmate1 ()
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals ("k1"))
		{
		    row = num;
		    col = num1;
		    break;
		}
	    }
	}


	int num1 = col;
	int num = row;
	if ((num >= 1 && num <= 6) && (num1 >= 1 && num1 <= 6))  //----------------------------CHECKS SURROUNDINGS FOR BLACK KING(LOWERCASE)
	{
	    if ((check_board [num - 1] [num1] == true) && (check_board [num - 1] [num1 - 1] == true) && (check_board [num - 1] [num1 + 1] == true) && (check_board [num] [num1 - 1] == true) && (check_board [num] [num1 + 1] == true) && (check_board [num + 1] [num1] == true) && (check_board [num + 1] [num1 - 1] == true) && (check_board [num + 1] [num1 + 1] == true))
	    {
		Blackcheckmate = true;
	    }
	}


	else if (num == 0)
	{
	    if ((check_board [num] [num1 - 1] == true) && (check_board [num] [num1 + 1] == true) && (check_board [num + 1] [num1] == true) && (check_board [num + 1] [num1 - 1] == true) && (check_board [num + 1] [num1 + 1] == true))
	    {
		Blackcheckmate = true;
	    }
	}


	else if (num == 7)
	{
	    if ((check_board [num - 1] [num1] == true) && (check_board [num - 1] [num1 - 1] == true) && (check_board [num - 1] [num1 + 1] == true) && (check_board [num] [num1 - 1] == true) && (check_board [num] [num1 + 1] == true))
	    {
		Blackcheckmate = true;
	    }
	}


	else if (num1 == 0)
	{
	    if ((check_board [num - 1] [num1] == true) && (check_board [num - 1] [num1 + 1] == true) && (check_board [num] [num1 + 1] == true) && (check_board [num + 1] [num1] == true) && (check_board [num + 1] [num1 + 1] == true))
	    {
		Blackcheckmate = true;
	    }
	}


	else if (num1 == 7)
	{
	    if ((check_board [num - 1] [num1] == true) && (check_board [num - 1] [num1 - 1] == true) && (check_board [num] [num1 - 1] == true) && (check_board [num + 1] [num1] == true) && (check_board [num + 1] [num1 - 1] == true))
	    {
		Blackcheckmate = true;
	    }
	}


	if (Blackcheckmate == true)
	{
	    System.out.println ("Checkmate!");
	}


	return Blackcheckmate;
    }


    boolean BlackKingCheck ()
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals ("k1"))
		{
		    row = num;
		    col = num1;
		    break;
		}
	    }
	}


	if (check_board [row] [col] == true)
	{
	    System.out.println ("Check!");
	    Blackcheck = true;
	}


	else
	{
	    Blackcheck = false;
	}


	return Blackcheck;
    }


    boolean WhiteKingCheck ()
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals ("K1"))
		{
		    row = num;
		    col = num1;
		    break;
		}
	    }
	}


	if (check_board2 [row] [col] == true)
	{
	    System.out.println ("Check!");
	    Whitecheck = true;
	}

	else
	{
	    Whitecheck = false;
	}


	return Whitecheck;
    }





    void setbooleanboardfree ()  //-----------resets boolean checking board
    {
	for (int num = 0 ; num <= 7 ; num++)
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		check_board [num] [num1] = false;
	    }
	}
    }


    void setbooleanboardfree2 ()  //-----------resets boolean checking board (player 2)
    {
	for (int num = 0 ; num <= 7 ; num++)
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		check_board2 [num] [num1] = false;
	    }
	}
    }


    // IF THE ELEMENT IS TRUE, KING CANNOT BE IN THE POSITION OF THE ARRAY
    void setbooleanboard ()
    {
	for (int num = 0 ; num <= 7 ; num++) //------------------------------------This boolean 2d array sets values for checkmate,stalemate, and check to be idenified
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (!board [num] [num1].equals ("__") && !board [num] [num1].equals ("k1") && !board [num] [num1].equals ("K1"))
		{
		    check_board [num] [num1] = true;
		}
		if (board [num] [num1].equals ("R1") || board [num] [num1].equals ("R2")) //----------ROOK
		{
		    for (int num4 = num - 1 ; num4 >= 0 ; num4--)
		    {
			String detect = board [num4] [num1];
			if (!detect.equals ("__"))
			{
			    break;
			}
			check_board [num4] [num1] = true;
		    }

		    for (int num4 = num + 1 ; num4 <= 7 ; num4++)
		    {
			String detect = board [num4] [num1];
			if (!detect.equals ("__"))
			{
			    break;
			}
			check_board [num4] [num1] = true;

		    }

		    for (int num4 = num1 - 1 ; num4 >= 0 ; num4--)
		    {
			String detect = board [num] [num4];

			if (!detect.equals ("__"))
			{
			    break;
			}

			check_board [num] [num4] = true;

		    }
		    for (int num4 = num1 + 1 ; num4 <= 7 ; num4++)
		    {
			String detect = board [num] [num4];

			if (!detect.equals ("__"))
			{
			    break;
			}

			check_board [num] [num4] = true;
		    }

		}
		if (board [num] [num1].equals ("N1") || board [num] [num1].equals ("N2")) //-----------knight
		{
		    if ((num1 == 0 || num1 == 1) && (num >= 2 && num <= 5))
		    {
			check_board [num + 1] [num1 + 2] = true;
			check_board [num - 1] [num1 + 2] = true;
			check_board [num + 2] [num1 + 1] = true;
			check_board [num - 2] [num1 + 1] = true;
			if (num1 == 1)
			{
			    check_board [num + 2] [num1 - 1] = true;
			    check_board [num - 2] [num1 - 1] = true;
			}

		    }
		    else if ((num1 == 6 || num1 == 7) && (num >= 2 && num <= 5))
		    {
			check_board [num + 1] [num1 - 2] = true;
			check_board [num - 1] [num1 - 2] = true;
			check_board [num + 2] [num1 - 1] = true;
			check_board [num - 2] [num1 - 1] = true;
			if (num1 == 6)
			{
			    check_board [num + 2] [num1 + 1] = true;
			    check_board [num - 2] [num1 + 1] = true;
			}
		    }
		    //---------------------------------------------------------------------------------------------------bottom
		    if ((num1 >= 2 && num1 <= 5) && (num >= 2 && num <= 5))
		    {
			check_board [num + 1] [num1 - 2] = true;
			check_board [num + 1] [num1 + 2] = true;
			check_board [num + 2] [num1 - 1] = true;
			check_board [num + 2] [num1 + 1] = true;
			check_board [num - 1] [num1 - 2] = true;
			check_board [num - 1] [num1 + 2] = true;
			check_board [num - 2] [num1 + 1] = true;
			check_board [num - 2] [num1 - 1] = true;

		    }
		    if ((num1 >= 2 && num1 <= 5) && (num == 0 || num == 1))
		    {
			check_board [num + 1] [num1 - 2] = true;
			check_board [num + 1] [num1 + 2] = true;
			check_board [num + 2] [num1 - 1] = true;
			check_board [num + 2] [num1 + 1] = true;
			if (num == 1)
			{
			    check_board [num - 1] [num1 + 2] = true;
			    check_board [num - 1] [num1 - 2] = true;
			}

		    }
		    else if ((num1 >= 2 && num1 <= 5) && (num == 6 || num == 7))
		    {
			check_board [num - 2] [num1 - 1] = true;
			check_board [num - 2] [num1 + 1] = true;
			check_board [num - 1] [num1 - 2] = true;
			check_board [num - 1] [num1 + 2] = true;
			if (num == 1)
			{
			    check_board [num + 1] [num1 + 2] = true;
			    check_board [num + 1] [num1 - 2] = true;
			}

		    }
		    //---------------------------------------------------------------------------4x4 corners
		    else if (num == 0 && num1 == 0)
		    {
			check_board [num - 2] [num1 + 1] = true;
			check_board [num - 1] [num1 + 2] = true;
		    }
		    else if (num == 0 && num1 == 7)
		    {
			check_board [num - 2] [num1 - 1] = true;
			check_board [num - 1] [num1 - 2] = true;
		    }
		    else if (num == 7 && num1 == 0)
		    {
			check_board [num - 1] [num1 + 2] = true;
			check_board [num - 2] [num1 + 1] = true;
		    }
		    else if (num == 7 && num1 == 7)
		    {
			check_board [num - 2] [num1 - 1] = true;
			check_board [num - 1] [num1 - 2] = true;
		    }


		    else if (num == 0 && num1 == 1)
		    {
			check_board [num + 2] [num1 - 1] = true;
			check_board [num + 2] [num1 + 1] = true;
			check_board [num + 1] [num1 + 2] = true;
		    }
		    else if (num == 0 && num1 == 6)
		    {
			check_board [num + 2] [num1 - 1] = true;
			check_board [num + 2] [num1 + 1] = true;
			check_board [num + 1] [num1 - 2] = true;
		    }
		    else if (num == 7 && num1 == 6)
		    {
			check_board [num - 2] [num1 - 1] = true;
			check_board [num - 2] [num1 + 1] = true;
			check_board [num - 1] [num1 - 2] = true;
		    }
		    else if (num == 0 && num1 == 6)
		    {
			check_board [num + 2] [num1 - 1] = true;
			check_board [num + 2] [num1 + 1] = true;
			check_board [num + 1] [num1 - 2] = true;
		    }


		    //---------------------------------------------------------------------errors check
		    else if (num == 6 && num1 == 0)
		    {
			check_board [num - 2] [num1 + 1] = true;
			check_board [num - 1] [num1 + 2] = true;
			check_board [num + 1] [num1 + 2] = true;
		    }
		    else if (num == 1 && num1 == 7)
		    {
			check_board [num + 2] [num1 - 1] = true;
			check_board [num + 1] [num1 - 2] = true;
			check_board [num - 1] [num1 - 2] = true;
		    }
		    else if (num == 1 && num1 == 0)
		    {
			check_board [num - 1] [num1 + 2] = true;
			check_board [num + 2] [num1 + 1] = true;
			check_board [num + 1] [num1 + 2] = true;
		    }
		    else if (num == 6 && num1 == 7)
		    {
			check_board [num - 2] [num1 - 1] = true;
			check_board [num - 1] [num1 - 2] = true;
			check_board [num + 1] [num1 - 2] = true;
		    }



		    else if (num == 1 && num1 == 1)
		    {
			check_board [num + 2] [num1 + 1] = true;
			check_board [num + 2] [num1 - 1] = true;
			check_board [num + 1] [num1 + 2] = true;
			check_board [num - 1] [num1 + 2] = true;
		    }
		    else if (num == 1 && num1 == 6)
		    {
			check_board [num + 2] [num1 + 1] = true;
			check_board [num + 2] [num1 - 1] = true;
			check_board [num + 1] [num1 - 2] = true;
			check_board [num - 1] [num1 - 2] = true;
		    }
		    else if (num == 6 && num1 == 1)
		    {
			check_board [num - 2] [num1 + 1] = true;
			check_board [num - 2] [num1 - 1] = true;
			check_board [num + 1] [num1 + 2] = true;
			check_board [num - 1] [num1 + 2] = true;
		    }
		    else if (num == 6 && num1 == 6)
		    {
			check_board [num - 2] [num1 + 1] = true;
			check_board [num - 2] [num1 - 1] = true;
			check_board [num + 1] [num1 - 2] = true;
			check_board [num - 1] [num1 - 2] = true;
		    }



		}

		if (board [num] [num1].equals ("P1")) //-------------------------------------setting values for possible spots P1 to move
		{
		    if (num1 == 0)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		}
		if (board [num] [num1].equals ("P2"))
		{
		    if (num1 == 0)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		}
		if (board [num] [num1].equals ("P3"))
		{
		    if (num1 == 0)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 = -1] = true;
			}
		    }
		    else
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		}
		if (board [num] [num1].equals ("P4"))
		{
		    if (num1 == 0)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		}
		if (board [num] [num1].equals ("P5"))
		{
		    if (num1 == 0)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		}
		if (board [num] [num1].equals ("P6"))
		{
		    if (num1 == 0)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		}
		if (board [num] [num1].equals ("P7"))
		{
		    if (num1 == 0)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		}
		if (board [num] [num1].equals ("P8"))
		{
		    if (num1 == 0)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 0)
			{
			    check_board [num - 1] [num1 + 1] = true;
			    check_board [num - 1] [num1 - 1] = true;
			}
		    }
		}
		if (board [num] [num1].equals ("B1"))
		{
		    y = num;
		    for (int x2 = num1 + 1 ; x2 <= 7 ; x2++) // Bishop Right
		    {
			y -= 1; // Bishop  up (Sub)
			if (y < 0)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 + 1 ; x2 <= 7 ; x2++) // Bishop Right
		    {
			y += 1; // Bishop  DOWN (Sub)
			if (y > 7)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 - 1 ; x2 >= 0 ; x2--) // Bishop left
		    {
			y -= 1; // Bishop  up (Sub)
			if (y < 0)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 - 1 ; x2 >= 0 ; x2--) // Bishop left
		    {
			y += 1; // Bishop  down (Sub)

			if (y > 7)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			    break;
			}
		    }
		}
		if (board [num] [num1].equals ("B2"))
		{
		    y = num;
		    for (int x2 = num1 + 1 ; x2 <= 7 ; x2++) // Bishop Right
		    {
			y -= 1; // Bishop  up (Sub)
			if (y < 0)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 + 1 ; x2 <= 7 ; x2++) // Bishop Right
		    {
			y += 1; // Bishop  DOWN (Sub)
			if (y > 7)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 - 1 ; x2 >= 0 ; x2--) // Bishop left
		    {
			y -= 1; // Bishop  up (Sub)
			if (y < 0)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 - 1 ; x2 >= 0 ; x2--) // Bishop left
		    {
			y += 1; // Bishop  down (Sub)
			if (y > 7)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			    break;
			}
		    }
		}
		if (board [num] [num1].equals ("K1"))
		{
		    if ((num >= 1 && num <= 6) && (num1 >= 1 && num1 <= 6))
		    {
			check_board [num - 1] [num1] = true;
			check_board [num - 1] [num1 - 1] = true;
			check_board [num - 1] [num1 + 1] = true;
			check_board [num] [num1 - 1] = true;
			check_board [num] [num1 + 1] = true;
			check_board [num + 1] [num1] = true;
			check_board [num + 1] [num1 - 1] = true;
			check_board [num + 1] [num1 + 1] = true;

		    }
		    else if (num == 0)
		    {
			check_board [num] [num1 - 1] = true;
			check_board [num] [num1 + 1] = true;
			check_board [num + 1] [num1] = true;
			check_board [num + 1] [num1 - 1] = true;
			check_board [num + 1] [num1 + 1] = true;

		    }
		    else if (num == 7)
		    {
			check_board [num - 1] [num1] = true;
			check_board [num - 1] [num1 - 1] = true;
			check_board [num - 1] [num1 + 1] = true;
			check_board [num] [num1 - 1] = true;
			check_board [num] [num1 + 1] = true;
		    }
		    else if (num1 == 0)
		    {
			check_board [num - 1] [num1] = true;
			check_board [num - 1] [num1 + 1] = true;
			check_board [num] [num1 + 1] = true;
			check_board [num + 1] [num1] = true;
			check_board [num + 1] [num1 + 1] = true;

		    }
		    else if (num1 == 7)
		    {
			check_board [num - 1] [num1] = true;
			check_board [num - 1] [num1 - 1] = true;
			check_board [num] [num1 - 1] = true;
			check_board [num + 1] [num1] = true;
			check_board [num + 1] [num1 - 1] = true;

		    }

		}

		if (board [num] [num1].equals ("Q1"))
		{
		    y = num;
		    for (int x2 = num1 + 1 ; x2 <= 7 ; x2++) // Bishop Right
		    {
			y -= 1; // Bishop  up (Sub)
			if (y < 0)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 + 1 ; x2 <= 7 ; x2++) // Bishop Right
		    {
			y += 1; // Bishop  DOWN (Sub)
			if (y > 7)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 - 1 ; x2 >= 0 ; x2--) // Bishop left
		    {
			y -= 1; // Bishop  up (Sub)
			if (y < 0)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board [y] [x2] = true;
			    break;
			}
		    }
		}
	    }
	}
    }

    void setbooleanboard2 ()
    {
	for (int num = 0 ; num <= 7 ; num++)
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (!board [num] [num1].equals ("__") && !board [num] [num1].equals ("k1") && !board [num] [num1].equals ("K1"))
		{
		    check_board2 [num] [num1] = true;
		}
		if (board [num] [num1].equals ("r1") || board [num] [num1].equals ("r2")) //-----------------------ROOK
		{
		    for (int num4 = num - 1 ; num4 >= 0 ; num4--)
		    {
			String detect = board [num4] [num1];
			if (!detect.equals ("__"))
			{
			    break;
			}
			check_board2 [num4] [num1] = true;
		    }

		    for (int num4 = num + 1 ; num4 <= 7 ; num4++)
		    {
			String detect = board [num4] [num1];
			if (!detect.equals ("__"))
			{
			    break;
			}
			check_board2 [num4] [num1] = true;

		    }

		    for (int num4 = num1 - 1 ; num4 >= 0 ; num4--)
		    {
			String detect = board [num] [num4];

			if (!detect.equals ("__"))
			{
			    break;
			}

			check_board2 [num] [num4] = true;

		    }
		    for (int num4 = num1 + 1 ; num4 <= 7 ; num4++)
		    {
			String detect = board [num] [num4];

			if (!detect.equals ("__"))
			{
			    break;
			}

			check_board2 [num] [num4] = true;
		    }

		}
		if (board [num] [num1].equals ("n1") || board [num] [num1].equals ("n2")) //-----------------------knight
		{
		    if ((num1 == 0 || num1 == 1) && (num >= 2 && num <= 5))
		    {
			check_board2 [num + 1] [num1 + 2] = true;
			check_board2 [num - 1] [num1 + 2] = true;
			check_board2 [num + 2] [num1 + 1] = true;
			check_board2 [num - 2] [num1 + 1] = true;
			if (num1 == 1)
			{
			    check_board2 [num + 2] [num1 - 1] = true;
			    check_board2 [num - 2] [num1 - 1] = true;
			}

		    }
		    else if ((num1 == 6 || num1 == 7) && (num >= 2 && num <= 5))
		    {
			check_board2 [num + 1] [num1 - 2] = true;
			check_board2 [num - 1] [num1 - 2] = true;
			check_board2 [num + 2] [num1 - 1] = true;
			check_board2 [num - 2] [num1 - 1] = true;
			if (num1 == 6)
			{
			    check_board2 [num + 2] [num1 + 1] = true;
			    check_board2 [num - 2] [num1 + 1] = true;
			}
		    }
		    //---------------------------------------------------------------------------------------------------bottom
		    if ((num1 >= 2 && num1 <= 5) && (num >= 2 && num <= 5))
		    {
			check_board2 [num + 1] [num1 - 2] = true;
			check_board2 [num + 1] [num1 + 2] = true;
			check_board2 [num + 2] [num1 - 1] = true;
			check_board2 [num + 2] [num1 + 1] = true;
			check_board2 [num - 1] [num1 - 2] = true;
			check_board2 [num - 1] [num1 + 2] = true;
			check_board2 [num - 2] [num1 + 1] = true;
			check_board2 [num - 2] [num1 - 1] = true;

		    }
		    if ((num1 >= 2 && num1 <= 5) && (num == 0 || num == 1))
		    {
			check_board2 [num + 1] [num1 - 2] = true;
			check_board2 [num + 1] [num1 + 2] = true;
			check_board2 [num + 2] [num1 - 1] = true;
			check_board2 [num + 2] [num1 + 1] = true;
			if (num == 1)
			{
			    check_board2 [num - 1] [num1 + 2] = true;
			    check_board2 [num - 1] [num1 - 2] = true;
			}

		    }
		    else if ((num1 >= 2 && num1 <= 5) && (num == 6 || num == 7))
		    {
			check_board2 [num - 2] [num1 - 1] = true;
			check_board2 [num - 2] [num1 + 1] = true;
			check_board2 [num - 1] [num1 - 2] = true;
			check_board2 [num - 1] [num1 + 2] = true;
			if (num == 1)
			{
			    check_board2 [num + 1] [num1 + 2] = true;
			    check_board2 [num + 1] [num1 - 2] = true;
			}

		    }
		    //---------------------------------------------------------------------------4x4 corners
		    else if (num == 0 && num1 == 0)
		    {
			check_board2 [num - 2] [num1 + 1] = true;
			check_board2 [num - 1] [num1 + 2] = true;
		    }
		    else if (num == 0 && num1 == 7)
		    {
			check_board2 [num - 2] [num1 - 1] = true;
			check_board2 [num - 1] [num1 - 2] = true;
		    }
		    else if (num == 7 && num1 == 0)
		    {
			check_board2 [num - 1] [num1 + 2] = true;
			check_board2 [num - 2] [num1 + 1] = true;
		    }
		    else if (num == 7 && num1 == 7)
		    {
			check_board2 [num - 2] [num1 - 1] = true;
			check_board2 [num - 1] [num1 - 2] = true;
		    }


		    else if (num == 0 && num1 == 1)
		    {
			check_board2 [num + 2] [num1 - 1] = true;
			check_board2 [num + 2] [num1 + 1] = true;
			check_board2 [num + 1] [num1 + 2] = true;
		    }
		    else if (num == 0 && num1 == 6)
		    {
			check_board2 [num + 2] [num1 - 1] = true;
			check_board2 [num + 2] [num1 + 1] = true;
			check_board2 [num + 1] [num1 - 2] = true;
		    }
		    else if (num == 7 && num1 == 6)
		    {
			check_board2 [num - 2] [num1 - 1] = true;
			check_board2 [num - 2] [num1 + 1] = true;
			check_board2 [num - 1] [num1 - 2] = true;
		    }
		    else if (num == 0 && num1 == 6)
		    {
			check_board2 [num + 2] [num1 - 1] = true;
			check_board2 [num + 2] [num1 + 1] = true;
			check_board2 [num + 1] [num1 - 2] = true;
		    }


		    //---------------------------------------------------------------------errors check
		    else if (num == 6 && num1 == 0)
		    {
			check_board2 [num - 2] [num1 + 1] = true;
			check_board2 [num - 1] [num1 + 2] = true;
			check_board2 [num + 1] [num1 + 2] = true;
		    }
		    else if (num == 1 && num1 == 7)
		    {
			check_board2 [num + 2] [num1 - 1] = true;
			check_board2 [num + 1] [num1 - 2] = true;
			check_board2 [num - 1] [num1 - 2] = true;
		    }
		    else if (num == 1 && num1 == 0)
		    {
			check_board2 [num - 1] [num1 + 2] = true;
			check_board2 [num + 2] [num1 + 1] = true;
			check_board2 [num + 1] [num1 + 2] = true;
		    }
		    else if (num == 6 && num1 == 7)
		    {
			check_board2 [num - 2] [num1 - 1] = true;
			check_board2 [num - 1] [num1 - 2] = true;
			check_board2 [num + 1] [num1 - 2] = true;
		    }



		    else if (num == 1 && num1 == 1)
		    {
			check_board2 [num + 2] [num1 + 1] = true;
			check_board2 [num + 2] [num1 - 1] = true;
			check_board2 [num + 1] [num1 + 2] = true;
			check_board2 [num - 1] [num1 + 2] = true;
		    }
		    else if (num == 1 && num1 == 6)
		    {
			check_board2 [num + 2] [num1 + 1] = true;
			check_board2 [num + 2] [num1 - 1] = true;
			check_board2 [num + 1] [num1 - 2] = true;
			check_board2 [num - 1] [num1 - 2] = true;
		    }
		    else if (num == 6 && num1 == 1)
		    {
			check_board2 [num - 2] [num1 + 1] = true;
			check_board2 [num - 2] [num1 - 1] = true;
			check_board2 [num + 1] [num1 + 2] = true;
			check_board2 [num - 1] [num1 + 2] = true;
		    }
		    else if (num == 6 && num1 == 6)
		    {
			check_board2 [num - 2] [num1 + 1] = true;
			check_board2 [num - 2] [num1 - 1] = true;
			check_board2 [num + 1] [num1 - 2] = true;
			check_board2 [num - 1] [num1 - 2] = true;
		    }



		}

		if (board [num] [num1].equals ("p1"))
		{
		    if (num1 == 0)
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		}
		if (board [num] [num1].equals ("p2"))
		{
		    if (num1 == 0)
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		}

		if (board [num] [num1].equals ("p3"))
		{
		    if (num1 == 0)
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		}

		if (board [num] [num1].equals ("p4"))
		{
		    if (num1 == 0)
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		}

		if (board [num] [num1].equals ("p5"))
		{
		    if (num1 == 0)
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		}

		if (board [num] [num1].equals ("p6"))
		{
		    if (num1 == 0)
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		}

		if (board [num] [num1].equals ("p7"))
		{
		    if (num1 == 0)
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		}

		if (board [num] [num1].equals ("p8"))
		{
		    if (num1 == 0)
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			}
		    }
		    else if (num1 == 7)
		    {
			if (num != 0)
			{
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		    else
		    {
			if (num != 7)
			{
			    check_board2 [num + 1] [num1 + 1] = true;
			    check_board2 [num + 1] [num1 - 1] = true;
			}
		    }
		}




		if (board [num] [num1].equals ("b1"))
		{
		    y = num;
		    for (int x2 = num1 + 1 ; x2 <= 7 ; x2++) // Bishop Right
		    {
			y -= 1; // Bishop  up (Sub)
			if (y < 0)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 + 1 ; x2 <= 7 ; x2++) // Bishop Right
		    {
			y += 1; // Bishop  DOWN (Sub)
			if (y > 7)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 - 1 ; x2 >= 0 ; x2--) // Bishop left
		    {
			y -= 1; // Bishop  up (Sub)
			if (y < 0)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 - 1 ; x2 >= 0 ; x2--) // Bishop left
		    {
			y += 1; // Bishop  down (Sub)

			if (y > 7)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			    break;
			}
		    }
		}
		if (board [num] [num1].equals ("b2"))
		{
		    y = num;
		    for (int x2 = num1 + 1 ; x2 <= 7 ; x2++) // Bishop Right
		    {
			y -= 1; // Bishop  up (Sub)
			if (y < 0)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 + 1 ; x2 <= 7 ; x2++) // Bishop Right
		    {
			y += 1; // Bishop  DOWN (Sub)
			if (y > 7)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 - 1 ; x2 >= 0 ; x2--) // Bishop left
		    {
			y -= 1; // Bishop  up (Sub)
			if (y < 0)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 - 1 ; x2 >= 0 ; x2--) // Bishop left
		    {
			y += 1; // Bishop  down (Sub)
			if (y > 7)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			    break;
			}
		    }
		}
		if (board [num] [num1].equals ("q1"))
		{
		    y = num;
		    for (int x2 = num1 + 1 ; x2 <= 7 ; x2++) // Bishop Right
		    {
			y -= 1; // Bishop  up (Sub)
			if (y < 0)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 + 1 ; x2 <= 7 ; x2++) // Bishop Right
		    {
			y += 1; // Bishop  DOWN (Sub)
			if (y > 7)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			    break;
			}
		    }
		    y = num;
		    for (int x2 = num1 - 1 ; x2 >= 0 ; x2--) // Bishop left
		    {
			y -= 1; // Bishop  up (Sub)
			if (y < 0)
			{
			    break;
			}

			if (board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			}
			else if (!board [y] [x2].equals ("__"))
			{
			    check_board2 [y] [x2] = true;
			    break;
			}
		    }
		}
	    }
	}
    }


    boolean Queen (String input)
	throws java.io.IOException
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals (input))
		{
		    ogrow = num;
		    ogcol = num1;
		    break;
		}
	    }
	}
	move = false;
	checker = false;
	do
	{
	    System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
	    row = (Integer.parseInt (br.readLine ()) - 1);
	    System.out.println ("");
	    System.out.print ("Column:");
	    col = (Integer.parseInt (br.readLine ()) - 1);
	    if (row == -2 || col == -2 || (row == -2 && col == -2))
	    {
		break;
	    }
	    if (ogrow - row == ogcol - col || -1 * (ogrow - row) == ogcol - col)
	    {
		y = ogrow;
		if (ogcol > col) // When Queen moves left (Title)
		{
		    checker = true;
		    for (x = ogcol - 1 ; x >= col ; x--)
		    {
			if (ogrow > row) // When Queen moves up (Sub)
			{
			    y -= 1;
			}
			else if (ogrow < row) // When Queen Moves down (Sub)
			{
			    y += 1;
			}
			String detect = board [y] [x];
			char ch = detect.charAt (0);
			boolean cpucheck = Character.isLowerCase (ch); // Checks Computer Pieces
			boolean playercheck = Character.isUpperCase (ch); // Checks Player Pieces
			if (cpucheck == true)
			{
			    if (x != col && y != row)
			    {

				checker = false;
				break;
			    }
			    if (x == col && y == row)
			    {
				if (checker == true)
				{
				    board [y] [x] = input;
				    move = true;
				    checker = true;
				    break;
				}

			    }
			}
			if (playercheck == true)
			{


			    checker = false;

			    break;
			}

		    }
		}

		else if (ogcol < col) // When Bishop move right
		{
		    checker = true;
		    for (x = ogcol + 1 ; x <= col ; x++)
		    {
			if (ogrow > row) // When Bishop moves down (Sub)
			{
			    y -= 1;
			}
			else if (ogrow < row) // When Bishop Moves up (Sub)
			{
			    y += 1;
			}
			else
			{
			}
			String detect = board [y] [x];
			char ch = detect.charAt (0);
			boolean cpucheck = Character.isLowerCase (ch); // Checks Computer Pieces
			boolean playercheck = Character.isUpperCase (ch); // Checks Player Pieces
			if (cpucheck == true)
			{
			    if (x != col && y != row)
			    {

				checker = false;
				break;
			    }
			    if (x == col && y == row)
			    {
				if (checker == true)
				{
				    board [y] [x] = input;
				    move = true;
				    checker = true;
				    break;
				}

			    }
			}
			if (playercheck == true)
			{
			    checker = false;
			    break;
			}
		    }
		}
		if (checker == true)
		{
		    board [row] [col] = input;
		    move = true;
		}
		break;
	    }
	    if (ogcol == col || ogrow == row)
	    {
		if (ogcol == col)
		{
		    if (ogrow > row)
		    {
			for (int num = ogrow - 1 ; num >= row ; num--)
			{
			    System.out.println ();
			    String detect = board [num] [col];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isLowerCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == row && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		    else
		    {
			for (int num = ogrow + 1 ; num <= row ; num++)
			{
			    System.out.println ();
			    String detect = board [num] [col];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isLowerCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == row && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		}
		else if (ogrow == row)
		{

		    if (ogcol > col)
		    {
			for (int num = ogcol - 1 ; num >= col ; num--)
			{
			    String detect = board [row] [num];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isLowerCase (ch);
			    if (detect.equals ("__"))
			    {

				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == col && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		    else
		    {
			for (int num = ogcol + 1 ; num <= col ; num++)
			{
			    System.out.println ();
			    String detect = board [row] [num];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isLowerCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == col && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		}
		if (breaker == 1)
		{
		    board [row] [col] = input;
		    move = true;
		    break;
		}
		System.out.println ("Please try again");
	    }
	    if (breaker == 1)
	    {
		break;
	    }
	}


	while (col != 425348465);
	if (breaker == 1 || checker == true)
	{
	    board [ogrow] [ogcol] = "__";
	    breaker = 0;
	    checker = true;
	}
	return move;
    }



    boolean Queen2 (String input)
	throws java.io.IOException
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals (input))
		{
		    ogrow = num;
		    ogcol = num1;
		    break;
		}
	    }

	}

	move = false;
	checker = false;
	do
	{
	    System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
	    row = (Integer.parseInt (br.readLine ()) - 1);
	    System.out.println ("");
	    System.out.print ("Column:");
	    col = (Integer.parseInt (br.readLine ()) - 1);
	    if (row == -2 || col == -2 || (row == -2 && col == -2))
	    {
		break;
	    }
	    if (ogrow - row == ogcol - col || -1 * (ogrow - row) == ogcol - col)
	    {
		y = ogrow;
		if (ogcol > col)
		{
		    checker = true;
		    for (x = ogcol - 1 ; x >= col ; x--) // When Queen moves left (Title) //MOVEMENT IS ORGANIZED BY (TITLE + SUB) (DIAGNOLLY)
		    {
			if (ogrow > row) // When Queen moves down (Sub)
			{
			    y -= 1;
			}
			else if (ogrow < row) // When Queen Moves up (Sub)
			{
			    y += 1;
			}
			else
			{
			}
			String detect = board [y] [x];
			char ch = detect.charAt (0);
			boolean cpucheck = Character.isUpperCase (ch); // Checks Computer Pieces
			boolean playercheck = Character.isLowerCase (ch); // Checks Player Pieces
			if (cpucheck == true)
			{
			    if (x != col && y != row)
			    {
				checker = false;
				break;
			    }
			    if (x == col && y == row)
			    {
				if (checker == true)
				{
				    board [y] [x] = input;
				    move = true;
				    checker = true;
				    break;
				}

			    }
			}
			if (playercheck == true)
			{
			    checker = false;
			    break;
			}

		    }
		}

		else if (ogcol < col) // When Queen move right
		{
		    checker = true;
		    for (x = ogcol + 1 ; x <= col ; x++)
		    {
			if (ogrow > row) // When Queen moves down (Sub)
			{
			    y -= 1;
			}
			else if (ogrow < row) // When Queen Moves up (Sub)
			{
			    y += 1;
			}
			else
			{
			}
			String detect = board [y] [x];
			char ch = detect.charAt (0);
			boolean cpucheck = Character.isUpperCase (ch); // Checks Computer Pieces
			boolean playercheck = Character.isLowerCase (ch); // Checks Player Pieces
			if (cpucheck == true)
			{
			    if (x != col && y != row)
			    {
				System.out.println (board [y] [x] + " is blocking this move");
				checker = false;
				break;
			    }
			    if (x == col && y == row)
			    {
				if (checker == true)
				{
				    board [y] [x] = input;
				    checker = true;
				    move = true;
				    break;
				}

			    }
			}
			if (playercheck == true)
			{
			    checker = false;
			    break;
			}
		    }
		}
		if (checker == true)
		{
		    board [row] [col] = input;
		}
		break;
	    }
	    if (ogcol == col || ogrow == row)
	    {
		if (ogcol == col)
		{
		    if (ogrow > row)
		    {
			for (int num = ogrow - 1 ; num >= row ; num--)
			{
			    System.out.println ();
			    String detect = board [num] [col];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isUpperCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == row && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		    else
		    {
			for (int num = ogrow + 1 ; num <= row ; num++)
			{
			    System.out.println ();
			    String detect = board [num] [col];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isUpperCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == row && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		}
		else if (ogrow == row)
		{

		    if (ogcol > col)
		    {
			for (int num = ogcol - 1 ; num >= col ; num--)
			{
			    String detect = board [row] [num];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isUpperCase (ch);
			    if (detect.equals ("__"))
			    {

				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == col && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		    else
		    {
			for (int num = ogcol + 1 ; num <= col ; num++)
			{
			    System.out.println ();
			    String detect = board [row] [num];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isUpperCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == col && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		}
		if (breaker == 1)
		{
		    board [row] [col] = input;
		    move = true;
		    break;
		}
		System.out.println ("Please try again");
	    }
	    if (breaker == 1)
	    {
		break;
	    }
	}


	while (col != 425348465);
	if (breaker == 1 || checker == true)
	{
	    board [ogrow] [ogcol] = "__";
	    breaker = 0;
	    checker = true;
	}
	return move;
    }




    boolean Bishopmovement (String input)
	throws java.io.IOException
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals (input))
		{
		    ogrow = num;
		    ogcol = num1;
		    break;
		}
	    }

	}
	checker = false;
	move = false;
	do
	{
	    System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
	    checker = true;
	    row = (Integer.parseInt (br.readLine ()) - 1);
	    System.out.println ("");
	    System.out.print ("Column:");
	    col = (Integer.parseInt (br.readLine ()) - 1);
	    if (row == -2 || col == -2 || (row == -2 && col == -2))
	    {
		break;
	    }
	    if (ogrow - row == ogcol - col || -1 * (ogrow - row) == ogcol - col)
	    {
		y = ogrow;
		if (ogcol > col) // When bishop moves left (Title)
		{
		    checker = true;
		    for (x = ogcol - 1 ; x >= col ; x--)
		    {
			if (ogrow > row) // When Bishop moves down (Sub)
			{
			    y -= 1;
			}
			else if (ogrow < row) // When Bishop Moves up (Sub)
			{
			    y += 1;
			}
			else
			{
			}
			String detect = board [y] [x];
			char ch = detect.charAt (0);
			boolean cpucheck = Character.isLowerCase (ch); // Checks Computer Pieces
			boolean playercheck = Character.isUpperCase (ch); // Checks Player Pieces
			if (cpucheck == false && playercheck == false)
			{
			    check_board [y] [x] = false;
			}
			if (cpucheck == true)
			{
			    if (x != col && y != row)
			    {
				// System.out.println (board [y] [x] + " is blocking this move");
				checker = false;
				break;
			    }
			    if (x == col && y == row)
			    {
				if (checker == true)
				{
				    board [y] [x] = input;
				    checker = true;
				    break;
				}

			    }
			}
			if (playercheck == true)
			{
			    checker = false;
			    break;
			}

		    }
		}
		else if (ogcol < col) // When Bishop move right
		{
		    checker = true;
		    for (x = ogcol + 1 ; x <= col ; x++)
		    {
			if (ogrow > row) // When Bishop moves down (Sub)
			{
			    y -= 1;
			}
			else if (ogrow < row) // When Bishop Moves up (Sub)
			{
			    y += 1;
			}
			else
			{
			}
			String detect = board [y] [x];
			char ch = detect.charAt (0);
			boolean cpucheck = Character.isLowerCase (ch); // Checks Computer Pieces
			boolean playercheck = Character.isUpperCase (ch); // Checks Player Pieces
			if (cpucheck == true)
			{
			    if (x != col && y != row)
			    {
				System.out.println (board [y] [x] + " is blocking this move");
				checker = false;
				break;
			    }
			    if (x == col && y == row)
			    {
				if (checker == true)
				{
				    board [y] [x] = input;
				    move = true;
				    checker = true;
				    break;
				}

			    }
			}
			if (playercheck == true)
			{
			    checker = false;
			    break;
			}
		    }
		}
		if (checker == true)
		{
		    board [row] [col] = input;
		    move = true;
		}
		break;
	    }
	}


	while (row != 21341);
	if (checker == true)
	{
	    board [ogrow] [ogcol] = "__";
	}
	return move;
    }


    boolean Bishopmovement2 (String input)
	throws java.io.IOException
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals (input))
		{
		    ogrow = num;
		    ogcol = num1;
		    break;
		}
	    }

	}
	move = false;
	checker = false;
	do
	{
	    System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
	    checker = true;
	    row = (Integer.parseInt (br.readLine ()) - 1);
	    System.out.println ("");
	    System.out.print ("Column:");
	    col = (Integer.parseInt (br.readLine ()) - 1);
	    if (row == -2 || col == -2 || (row == -2 && col == -2))
	    {
		break;
	    }
	    if (ogrow - row == ogcol - col || -1 * (ogrow - row) == ogcol - col)
	    {
		y = ogrow;
		if (ogcol > col) // When bishop moves left (Title)
		{
		    checker = true;
		    for (x = ogcol - 1 ; x >= col ; x--)
		    {
			if (ogrow > row) // When Bishop moves down (Sub)
			{
			    y -= 1;
			}
			else if (ogrow < row) // When Bishop Moves up (Sub)
			{
			    y += 1;
			}
			else
			{
			}
			String detect = board [y] [x];
			char ch = detect.charAt (0);
			boolean cpucheck = Character.isUpperCase (ch); // Checks Computer Pieces
			boolean playercheck = Character.isLowerCase (ch); // Checks Player Pieces
			if (cpucheck == false && playercheck == false)
			{
			    check_board [y] [x] = false;
			}
			if (cpucheck == true)
			{
			    if (x != col && y != row)
			    {
				// System.out.println (board [y] [x] + " is blocking this move");
				checker = false;
				break;
			    }
			    if (x == col && y == row)
			    {
				if (checker == true)
				{
				    board [y] [x] = input;
				    checker = true;
				    break;
				}

			    }
			}
			if (playercheck == true)
			{
			    checker = false;
			    break;
			}

		    }
		}
		else if (ogcol < col) // When Bishop move right
		{
		    checker = true;
		    for (x = ogcol + 1 ; x <= col ; x++)
		    {
			if (ogrow > row) // When Bishop moves down (Sub)
			{
			    y -= 1;
			}
			else if (ogrow < row) // When Bishop Moves up (Sub)
			{
			    y += 1;
			}
			else
			{
			}
			String detect = board [y] [x];
			char ch = detect.charAt (0);
			boolean cpucheck = Character.isUpperCase (ch); // Checks Computer Pieces
			boolean playercheck = Character.isLowerCase (ch); // Checks Player Pieces
			if (cpucheck == true)
			{
			    if (x != col && y != row)
			    {
				System.out.println (board [y] [x] + " is blocking this move");
				checker = false;
				break;
			    }
			    if (x == col && y == row)
			    {
				if (checker == true)
				{
				    board [y] [x] = input;
				    move = true;
				    checker = true;
				    break;
				}

			    }
			}
			if (playercheck == true)
			{
			    checker = false;
			    break;
			}
		    }
		}
		if (checker == true)
		{
		    board [row] [col] = input;
		    move = true;
		}
		break;
	    }
	}
	while (row != 21341);
	if (checker == true)
	{
	    board [ogrow] [ogcol] = "__";
	}
	return move;
    }


    boolean King (String input)
	throws java.io.IOException
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals (input))
		{
		    ogrow = num;
		    ogcol = num1;
		    break;
		}
	    }

	}
	move = false;

	do
	{
	    int breaker = 0;
	    System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
	    row = (Integer.parseInt (br.readLine ()) - 1);
	    System.out.println ("");
	    System.out.print ("Column:");
	    col = (Integer.parseInt (br.readLine ()) - 1);
	    if (row == -2 || col == -2 || (row == -2 && col == -2))
	    {
		break;
	    }
	    char ch = board [row] [col].charAt (0);
	    boolean checkL = Character.isLowerCase (ch);
	    if ((ogrow - 1 == row && ogcol == col) || (ogrow - 1 == row && ogcol - 1 == col) || (ogrow == row && ogcol - 1 == col) || (ogrow == row && ogcol + 1 == col) || (ogrow + 1 == row && ogcol - 1 == col) || (ogrow + 1 == row && ogcol == col) || (ogrow + 1 == row && ogcol + 1 == col))
		//IF STATEMENT CHECKS IF MOVEMENT OF KING IS BY 1 IN ANY DIRECTION
		{
		    if (check_board2 [row] [col] == true)
		    {
			System.out.println ("Please try again");
		    }
		    else if (board [row] [col].equals ("__"))
		    {
			board [row] [col] = input;
			move = true;
			break;
		    }
		    else if (Character.isLowerCase (ch))
		    {
			board [row] [col] = input;
			move = true;
			break;
		    }
		    else if (Character.isUpperCase (ch))
		    {
			System.out.println ("Please try again");
		    }
		}
	}


	while (col != 425348465)
	    ;
	if (move == true)
	{
	    board [ogrow] [ogcol] = "__";

	}
	return move;
    }


    boolean King2 (String input)
	throws java.io.IOException
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals (input))
		{
		    ogrow = num;
		    ogcol = num1;
		    break;
		}
	    }

	}
	move = false;

	do
	{
	    int breaker = 0;
	    System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
	    row = (Integer.parseInt (br.readLine ()) - 1);
	    System.out.println ("");
	    System.out.print ("Column:");
	    col = (Integer.parseInt (br.readLine ()) - 1);
	    if (row == -2 || col == -2 || (row == -2 && col == -2))
	    {
		break;
	    }
	    char ch = board [row] [col].charAt (0);
	    boolean checkL = Character.isUpperCase (ch);
	    if ((ogrow - 1 == row && ogcol == col) || (ogrow - 1 == row && ogcol - 1 == col) || (ogrow == row && ogcol - 1 == col) || (ogrow == row && ogcol + 1 == col) || (ogrow + 1 == row && ogcol - 1 == col) || (ogrow + 1 == row && ogcol == col) || (ogrow + 1 == row && ogcol + 1 == col))
	    {
		if (check_board [row] [col] == true)
		{
		    System.out.println ("Please try again");
		}
		else if (board [row] [col].equals ("__"))
		{
		    board [row] [col] = input;
		    move = true;
		    break;
		}
		else if (Character.isUpperCase (ch))
		{

		    board [row] [col] = input;
		    move = true;
		    break;
		}
		else if (Character.isLowerCase (ch))
		{
		    System.out.println ("Please try again");
		}

	    }
	}
	while (col != 425348465)
	    ;
	if (move == true)
	{
	    board [ogrow] [ogcol] = "__";

	}
	return move;
    }


    boolean Rook (String input)
	throws java.io.IOException
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals (input))
		{
		    ogrow = num;
		    ogcol = num1;
		    break;
		}
	    }

	}
	move = false;

	do
	{
	    int breaker = 0;
	    System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
	    row = (Integer.parseInt (br.readLine ()) - 1);
	    System.out.println ("");
	    System.out.print ("Column:");
	    col = (Integer.parseInt (br.readLine ()) - 1);
	    if (row == -2 || col == -2 || (row == -2 && col == -2))
	    {
		break;
	    }
	    if (ogcol == col || ogrow == row)
	    {
		if (ogcol == col)
		{
		    if (ogrow > row) //----------------------------------if user chooses to go down
		    {
			for (int num = ogrow - 1 ; num >= row ; num--)
			{
			    System.out.println ();
			    String detect = board [num] [col];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isLowerCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == row && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		    else //----------------------------------if user chooses to go up
		    {
			for (int num = ogrow + 1 ; num <= row ; num++)
			{
			    System.out.println ();
			    String detect = board [num] [col];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isLowerCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == row && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		}
		else if (ogrow == row)
		{
		    if (ogcol > col) //----------------------------------if user chooses to go right
		    {
			for (int num = ogcol - 1 ; num >= col ; num--)
			{
			    System.out.println ();
			    String detect = board [row] [num];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isLowerCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == col && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		    else //----------------------------------if user chooses to go left
		    {
			for (int num = ogcol + 1 ; num <= col ; num++)
			{
			    String detect = board [row] [num];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isLowerCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == col && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		}
		if (breaker == 1)
		{
		    board [row] [col] = input;
		    move = true;
		    break;
		}
		System.out.println ("Please try again");
	    }
	}


	while (col != 425348465)
	    ;
	if (move == true)
	{
	    board [ogrow] [ogcol] = "__";

	}
	return move;
    }


    boolean Rook2 (String input)
	throws java.io.IOException
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals (input))
		{
		    ogrow = num;
		    ogcol = num1;
		    break;
		}
	    }

	}
	move = false;

	do
	{
	    int breaker = 0;
	    System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
	    row = (Integer.parseInt (br.readLine ()) - 1);
	    System.out.println ("");
	    System.out.print ("Column:");
	    col = (Integer.parseInt (br.readLine ()) - 1);
	    if (row == -2 || col == -2 || (row == -2 && col == -2))
	    {
		break;
	    }
	    if (ogcol == col || ogrow == row)
	    {
		if (ogcol == col)
		{
		    if (ogrow > row)
		    {
			for (int num = ogrow - 1 ; num >= row ; num--)
			{
			    System.out.println ();
			    String detect = board [num] [col];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isUpperCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == row && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		    else
		    {
			for (int num = ogrow + 1 ; num <= row ; num++)
			{
			    System.out.println ();
			    String detect = board [num] [col];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isUpperCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == row && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		}
		else if (ogrow == row)
		{
		    if (ogcol > col)
		    {
			for (int num = ogcol - 1 ; num >= col ; num--)
			{
			    System.out.println ();
			    String detect = board [row] [num];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isUpperCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == col && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		    else
		    {
			for (int num = ogcol + 1 ; num <= col ; num++)
			{
			    System.out.println ();
			    String detect = board [row] [num];
			    char ch = detect.charAt (0);
			    boolean checkL = Character.isUpperCase (ch);
			    if (detect.equals ("__"))
			    {
				breaker = 1;
			    }
			    else
			    {
				breaker = 0;

			    }
			    if (breaker == 0)
			    {
				if (num == col && checkL == true)
				{
				    breaker = 1;
				    break;
				}
				else
				{
				    breaker = 0;
				    break;
				}
			    }
			}
		    }
		}
		if (breaker == 1)
		{
		    board [row] [col] = input;
		    move = true;
		    break;
		}
		System.out.println ("Please try again");
	    }
	}


	while (col != 425348465)
	    ;
	if (move == true)
	{
	    board [ogrow] [ogcol] = "__";

	}
	return move;
    }


    boolean Knight (String input)
	throws java.io.IOException
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals (input))
		{
		    ogrow = num;
		    ogcol = num1;
		    break;
		}
	    }

	}

	move = false;
	if (input.equals ("N1"))
	{
	    do
	    {
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		if ((ogrow - 2 == row && ogcol - 1 == col) || (ogrow - 2 == row && ogcol + 1 == col) || (ogrow - 1 == row && ogcol - 2 == col) || (ogrow - 1 == row && ogcol + 2 == col) || (ogrow + 1 == row && ogcol - 2 == col) || (ogrow + 1 == row && ogcol + 2 == col) || (ogrow + 2 == row && ogcol - 1 == col) || (ogrow + 2 == row && ogcol + 1 == col))
		    // IF STATEMENT CHECKS IF THE PECULIAR MOVEMENT OF KNIGHT HAS BEEN ENTERED BY THE USER
		    {
			if (board [row] [col] == "__") // CHECKS IF ENTERED SPOT IS EMPTY
			{

			    board [row] [col] = input;
			    move = true;
			    break;
			}
			String detect = board [row] [col];
			char ch = detect.charAt (0);
			boolean check = Character.isLowerCase (ch); // IF THERE IS A A LOWECASE PIECE......
			if (check == true)
			{
			    board [ogrow] [ogcol] = "__";
			    board [row] [col] = input; //TAKES OVER LOWERCASE SPOT
			    move = true;
			    break;
			}
		    }
	    }
	    while (col != 425348465)
		;
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}


	if (input.equals ("N2")) // Same structure as N1
	{
	    do
	    {
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		if ((ogrow - 2 == row && ogcol - 1 == col) || (ogrow - 2 == row && ogcol + 1 == col) || (ogrow - 1 == row && ogcol - 2 == col) || (ogrow - 1 == row && ogcol + 2 == col) || (ogrow + 1 == row && ogcol - 2 == col) || (ogrow + 1 == row && ogcol + 2 == col) || (ogrow + 2 == row && ogcol - 1 == col) || (ogrow + 2 == row && ogcol + 1 == col))
		{
		    if (board [row] [col] == "__")
		    {
			board [row] [col] = input;
			move = true;
			break;
		    }
		    String detect = board [row] [col];
		    char ch = detect.charAt (0);
		    boolean check = Character.isLowerCase (ch);
		    if (check == true)
		    {
			board [ogrow] [ogcol] = "__";
			board [row] [col] = input;
			move = true;
			break;
		    }
		}
	    }
	    while (col != 425348465)
		;
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }


	}
	return move;
    }


    boolean Knight2 (String input)
	throws java.io.IOException
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals (input))
		{
		    ogrow = num;
		    ogcol = num1;
		    break;
		}
	    }

	}

	move = false;
	if (input.equals ("n1")) // Same structure as N1
	{
	    do
	    {
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		if ((ogrow - 2 == row && ogcol - 1 == col) || (ogrow - 2 == row && ogcol + 1 == col) || (ogrow - 1 == row && ogcol - 2 == col) || (ogrow - 1 == row && ogcol + 2 == col) || (ogrow + 1 == row && ogcol - 2 == col) || (ogrow + 1 == row && ogcol + 2 == col) || (ogrow + 2 == row && ogcol - 1 == col) || (ogrow + 2 == row && ogcol + 1 == col))
		{
		    if (board [row] [col] == "__")
		    {

			board [row] [col] = input;
			move = true;
			break;
		    }
		    String detect = board [row] [col];
		    char ch = detect.charAt (0);
		    boolean check = Character.isUpperCase (ch);
		    if (check == true)
		    {
			board [ogrow] [ogcol] = "__";
			board [row] [col] = input;
			move = true;
			break;
		    }
		}
	    }
	    while (col != 425348465)
		;
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}


	if (input.equals ("n2")) // Same structure as N1
	{
	    do
	    {
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		if ((ogrow - 2 == row && ogcol - 1 == col) || (ogrow - 2 == row && ogcol + 1 == col) || (ogrow - 1 == row && ogcol - 2 == col) || (ogrow - 1 == row && ogcol + 2 == col) || (ogrow + 1 == row && ogcol - 2 == col) || (ogrow + 1 == row && ogcol + 2 == col) || (ogrow + 2 == row && ogcol - 1 == col) || (ogrow + 2 == row && ogcol + 1 == col))
		{
		    if (board [row] [col] == "__")
		    {
			board [row] [col] = input;
			move = true;
			break;
		    }
		    String detect = board [row] [col];
		    char ch = detect.charAt (0);
		    boolean check = Character.isUpperCase (ch);
		    if (check == true)
		    {
			board [ogrow] [ogcol] = "__";
			board [row] [col] = input;
			move = true;
			break;
		    }
		}
	    }
	    while (col != 425348465)
		;
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }


	}
	return move;
    }


    void emptyboard ()
    {

	for (int row = 0 ; row <= 7 ; row++)
	{
	    for (int col = 0 ; col <= 7 ; col++)
	    {
		board [row] [col] = "__";
	    }
	}

	// SETTING BOARD PIECES (START OF GAME)
	// Black Pieces
	board [0] [0] = "r1";
	board [0] [1] = "n1";
	board [0] [2] = "b1";
	board [0] [3] = "q1";
	board [0] [4] = "k1";
	board [0] [5] = "b2";
	board [0] [6] = "n2";
	board [0] [7] = "r2";
	board [1] [0] = "p1";
	board [1] [1] = "p2";
	board [1] [2] = "p3";
	board [1] [3] = "p4";
	board [1] [4] = "p5";
	board [1] [5] = "p6";
	board [1] [6] = "p7";
	board [1] [7] = "p8";
	// White Pieces
	board [7] [0] = "R1";
	board [7] [1] = "N1";
	board [7] [2] = "B1";
	board [7] [3] = "Q1";
	board [7] [4] = "K1";
	board [7] [5] = "B2";
	board [7] [6] = "N2";
	board [7] [7] = "R2";
	board [6] [0] = "P1";
	board [6] [1] = "P2";
	board [6] [2] = "P3";
	board [6] [3] = "P4";
	board [6] [4] = "P5";
	board [6] [5] = "P6";
	board [6] [6] = "P7";
	board [6] [7] = "P8";

    }


    void showboard ()  // MAIN DISPLAY OF GAME

    {
	System.out.println ("\t   PLAYER  2 (LOWERCASE)");
	System.out.println ("     1   2   3   4   5   6   7   8");
	System.out.println ("  +----------------------------------+");
	for (int row = 0 ; row <= 7 ; row++)
	{
	    System.out.print ((row + 1) + " |  ");
	    for (int col = 0 ; col <= 7 ; col++)
	    {
		System.out.print (board [row] [col] + "  ");
	    }
	    System.out.println ("|\r  |                                  |");
	}


	System.out.println ("  +----------------------------------+");
	System.out.println ("\t   PLAYER  1 (UPPERCASE)");
    }


    void showbooleanboard ()

    {
	System.out.println ("\t   PLAYER  2 (LOWERCASE)");
	System.out.println ("  +----------------------------------------------------------------------+");
	for (int row = 0 ; row <= 7 ; row++)
	{
	    System.out.print ((row + 1) + "   ");
	    for (int col = 0 ; col <= 7 ; col++)
	    {
		System.out.print (check_board2 [row] [col] + "  ");
	    }
	    System.out.println ("\r                      ");
	}


	System.out.println ("  +----------------------------------+");
	System.out.println ("\t   PLAYER  1 (UPPERCASE)");
    }




    boolean pawnmovement (String input)
	throws java.io.IOException
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals (input))
		{
		    ogrow = num;
		    ogcol = num1;
		    break;
		}
	    }

	}
	move = false;

	if (input.equals ("P1")) //THERE ARE SEPERATE METHOD PAWN MOVEMENTS FOR EACH PAWN AS THE START OF A PAWN MOVEMENT, THE PAWN CAN MOVE TWICE AND SO A BOOLEAN IS REQUIRED TO CHECK IF EACH INDVIDIUAL PAWN HAS MOVED
	    //                          PAWN METHODS FUNCTION AND ARE STRUCTERED SAME (COMMENT CODES WOULD BE SAME)
	    {
		do
		{
		    boolean checker = true;
		    System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		    row = (Integer.parseInt (br.readLine ()) - 1);
		    System.out.println ("");
		    System.out.print ("Column:");
		    col = (Integer.parseInt (br.readLine ()) - 1);
		    if (row == -2 || col == -2 || (row == -2 && col == -2)) //checks if user has entered the emergency back button if they do not want to move the piece
		    {
			break;
		    }
		    if (!board [row] [ogcol].equals ("__")) //check if row and col entered is taken already
		    {
			checker = false;

		    }
		    if (P1check == 0 && checker == true) //checks if pawn hasn't moved
		    {
			if (ogrow - row <= 2 && ogrow - row > 0) // checks if pawn movement is Vertical by 2 up
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				P1check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow - 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow - 1] [ogcol - 1].equals ("__"))) //Checks if diagnol tiles are filled
		    {
			if (ogrow - row == 1 && col - ogcol == 1 || col - ogcol == -1) // checks if pawn movement is Vertical by 1 up
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isLowerCase (ch); //checks for Lowercase Piece
			    if (check == true) //if True, takes the piece away and contains the new Spot
			    {
				board [row] [col] = input;
				P1check += 1;
				move = true;
				break;
			    }
			}

		    }

		    if (P1check >= 1 && checker == true) //Simple movement vertical up
		    {
			if (ogrow - row == 1)
			{
			    if (ogcol - col == 0)
			    {
				P1check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}

		while (row != 21341)
		    ;
		if (move == true)
		{
		    board [ogrow] [ogcol] = "__";

		}
	    }


	if (input.equals ("P2")) //PAWN 2 // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		if (row == -2 || col == -2)
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (P2check == 0 && checker == true)
		    {
			if (ogrow - row <= 2 && ogrow - row > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				P2check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow - 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow - 1] [ogcol - 1].equals ("__")))
		    {
			if (ogrow - row == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isLowerCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				P2check += 1;
				move = true;
				break;
			    }
			}

		    }

		    if (P2check >= 1 && checker == true)
		    {
			if (ogrow - row == 1)
			{
			    if (ogcol - col == 0)
			    {
				P2check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341)
		;
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}


	if (input.equals ("P3")) //PAWN 3 // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (P3check == 0 && checker == true)
		    {
			if (ogrow - row <= 2 && ogrow - row > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				P3check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow - 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow - 1] [ogcol - 1].equals ("__")))
		    {
			if (ogrow - row == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isLowerCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				P3check += 1;
				move = true;
				break;
			    }
			}

		    }

		    if (P3check >= 1 && checker == true)
		    {
			if (ogrow - row == 1)
			{
			    if (ogcol - col == 0)
			    {
				P3check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341)
		;
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}


	if (input.equals ("P4")) //PAWN 4 // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (P4check == 0 && checker == true)
		    {
			if (ogrow - row <= 2 && ogrow - row > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				P4check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow - 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow - 1] [ogcol - 1].equals ("__")))
		    {
			if (ogrow - row == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isLowerCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				P4check += 1;
				move = true;
				break;
			    }
			}

		    }

		    if (P4check >= 1 && checker == true)
		    {
			if (ogrow - row == 1)
			{
			    if (ogcol - col == 0)
			    {
				P4check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341)
		;
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}


	if (input.equals ("P5")) //PAWN 5 // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (P5check == 0 && checker == true)
		    {
			if (ogrow - row <= 2 && ogrow - row > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				P5check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow - 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow - 1] [ogcol - 1].equals ("__")))
		    {
			if (ogrow - row == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isLowerCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				P5check += 1;
				move = true;
				break;
			    }
			}

		    }

		    if (P5check >= 1 && checker == true)
		    {
			if (ogrow - row == 1)
			{
			    if (ogcol - col == 0)
			    {
				P5check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341)
		;
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}


	if (input.equals ("P6")) //PAWN 6  // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (P6check == 0 && checker == true)
		    {
			if (ogrow - row <= 2 && ogrow - row > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				P6check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow - 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow - 1] [ogcol - 1].equals ("__")))
		    {
			if (ogrow - row == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isLowerCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				P6check += 1;
				move = true;
				break;
			    }
			}

		    }

		    if (P6check >= 1 && checker == true)
		    {
			if (ogrow - row == 1)
			{
			    if (ogcol - col == 0)
			    {
				P6check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341)
		;
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}


	if (input.equals ("P7")) //PAWN 7  // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (P7check == 0 && checker == true)
		    {
			if (ogrow - row <= 2 && ogrow - row > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				P7check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow - 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow - 1] [ogcol - 1].equals ("__")))
		    {
			if (ogrow - row == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isLowerCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				P7check += 1;
				move = true;
				break;
			    }
			}

		    }

		    if (P7check >= 1 && checker == true)
		    {
			if (ogrow - row == 1)
			{
			    if (ogcol - col == 0)
			    {
				P7check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341)
		;
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	    return move;
	}


	if (input.equals ("P8")) //PAWN 8 // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (P8check == 0 && checker == true)
		    {
			if (ogrow - row <= 2 && ogrow - row > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				P8check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow - 1] [ogcol + 1].equals ("__") || (ogcol != 7 && !board [ogrow - 1] [ogcol - 1].equals ("__")))
		    {
			if (ogrow - row == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isLowerCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				P8check += 1;
				move = true;
				break;
			    }
			}

		    }

		    if (P8check >= 1 && checker == true)
		    {
			if (ogrow - row == 1)
			{
			    if (ogcol - col == 0)
			    {
				P8check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341)
		;
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	    return move;
	}


	return move;
    }


    boolean pawnmovement2 (String input)
	throws java.io.IOException
    {
	for (int num = 0 ; num <= 7 ; num++) //----------------------------INDEX
	{
	    for (int num1 = 0 ; num1 <= 7 ; num1++)
	    {
		if (board [num] [num1].equals (input))
		{
		    ogrow = num;
		    ogcol = num1;
		    break;
		}
	    }
	}


	move = false;
	if (input.equals ("p1")) //PAWN 1  // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (p1check == 0 && checker == true)
		    {
			if (row - ogrow <= 2 && row - ogrow > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				p1check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow + 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow + 1] [ogcol - 1].equals ("__"))) //
		    {
			if (row - ogrow == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isUpperCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				move = true;
				p1check += 1;
				break;
			    }
			}

		    }

		    if (p1check >= 1 && checker == true)
		    {
			if (row - ogrow == 1)
			{
			    if (col - ogcol == 0)
			    {
				p1check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341);
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}
	if (input.equals ("p2")) //PAWN 2  // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (p2check == 0 && checker == true)
		    {
			if (row - ogrow <= 2 && row - ogrow > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				p2check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow + 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow + 1] [ogcol - 1].equals ("__"))) //
		    {
			if (row - ogrow == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isUpperCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				p2check += 1;
				move = true;
				break;
			    }
			}

		    }

		    if (p2check >= 1 && checker == true)
		    {
			if (row - ogrow == 1)
			{
			    if (col - ogcol == 0)
			    {
				p2check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341);
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}


	if (input.equals ("p3")) // PAWN 3  // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (p3check == 0 && checker == true)
		    {
			if (row - ogrow <= 2 && row - ogrow > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				p3check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow + 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow + 1] [ogcol - 1].equals ("__"))) //
		    {
			if (row - ogrow == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isUpperCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				p3check += 1;
				move = true;
				break;
			    }
			}

		    }

		    if (p3check >= 1 && checker == true)
		    {
			if (row - ogrow == 1)
			{
			    if (col - ogcol == 0)
			    {
				p3check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341);
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}


	if (input.equals ("p4")) // PAWN 4  // Same structure as P1
	{

	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (p4check == 0 && checker == true)
		    {
			if (row - ogrow <= 2 && row - ogrow > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				p4check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow + 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow + 1] [ogcol - 1].equals ("__"))) //
		    {
			if (row - ogrow == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isUpperCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				p4check += 1;
				move = true;
				break;
			    }
			}

		    }

		    if (p4check >= 1 && checker == true)
		    {
			if (row - ogrow == 1)
			{
			    if (col - ogcol == 0)
			    {
				p4check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341);
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}


	if (input.equals ("p5")) //PAWN 5  // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (p5check == 0 && checker == true)
		    {
			if (row - ogrow <= 2 && row - ogrow > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				p5check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow + 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow + 1] [ogcol - 1].equals ("__"))) //
		    {
			if (row - ogrow == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isUpperCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				p5check += 1;
				move = true;
				break;
			    }
			}

		    }

		    if (p5check >= 1 && checker == true)
		    {
			if (row - ogrow == 1)
			{
			    if (col - ogcol == 0)
			    {
				p5check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341);
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}


	if (input.equals ("p6")) // PAWN 6  // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (p6check == 0 && checker == true)
		    {
			if (row - ogrow <= 2 && row - ogrow > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				p6check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow + 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow + 1] [ogcol - 1].equals ("__"))) //
		    {
			if (row - ogrow == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isUpperCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				p6check += 1;
				move = true;
				break;
			    }
			}

		    }

		    if (p6check >= 1 && checker == true)
		    {
			if (row - ogrow == 1)
			{
			    if (col - ogcol == 0)
			    {
				p6check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341);
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}


	if (input.equals ("p7")) //PAWN 7  // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (p7check == 0 && checker == true)
		    {
			if (row - ogrow <= 2 && row - ogrow > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				move = true;
				p7check += 1;
				break;
			    }
			}
		    }
		    if (!board [ogrow + 1] [ogcol + 1].equals ("__") || (ogcol != 0 && !board [ogrow + 1] [ogcol - 1].equals ("__"))) //
		    {
			if (row - ogrow == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isUpperCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				move = true;
				p7check += 1;
				break;
			    }
			}

		    }

		    if (p7check >= 1 && checker == true)
		    {
			if (row - ogrow == 1)
			{
			    if (col - ogcol == 0)
			    {
				p7check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341);
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}




	if (input.equals ("p8")) //PAWN 8  // Same structure as P1
	{
	    do
	    {
		boolean checker = true;
		System.out.print ("Where would you like to move\rEnter '-1' to return and choose another piece\rRow:");
		row = (Integer.parseInt (br.readLine ()) - 1);
		System.out.println ("");
		System.out.print ("Column:");
		col = (Integer.parseInt (br.readLine ()) - 1);
		if (row == -2 || col == -2 || (row == -2 && col == -2))
		{
		    break;
		}
		{
		    if (!board [row] [ogcol].equals ("__"))
		    {
			checker = false;

		    }

		    if (p8check == 0 && checker == true)
		    {
			if (row - ogrow <= 2 && row - ogrow > 0)
			{
			    if (col - ogcol == 0)
			    {
				board [row] [col] = input;
				p8check += 1;
				move = true;
				break;
			    }
			}
		    }
		    if (!board [ogrow + 1] [ogcol].equals ("__") || (ogcol != 0 && !board [ogrow + 1] [ogcol - 1].equals ("__"))) //
		    {
			if (row - ogrow == 1 && col - ogcol == 1 || col - ogcol == -1)
			{
			    String detect = board [row] [col];
			    char ch = detect.charAt (0);
			    boolean check = Character.isUpperCase (ch);
			    if (check == true)
			    {
				board [row] [col] = input;
				move = true;
				p8check += 1;
				break;
			    }
			}

		    }

		    if (p8check >= 1 && checker == true)
		    {
			if (row - ogrow == 1)
			{
			    if (col - ogcol == 0)
			    {
				p8check += 1;
				System.out.println ("Done");
				board [row] [col] = input;
				move = true;
				break;
			    }
			}
		    }
		}
	    }
	    while (row != 21341);
	    if (move == true)
	    {
		board [ogrow] [ogcol] = "__";

	    }
	}

	return move;

	//----------------------------------------------------------------------------------------------p2
    }


    boolean passswordlengthcheck (String pw)
    {
	boolean check = false;
	if (pw.length () > 5 && pw.length () < 13)
	{
	    check = true;
	}


	else
	{
	    check = false;
	    System.out.println ("The Password is not valid due to the size");
	}


	return check;
    }


    boolean PasswordUpperCaseCheck (String pw)
    {
	boolean check = false;
	for (int count = 0 ; count < pw.length () ; count++)
	{
	    char ch = pw.charAt (count);
	    check = Character.isUpperCase (ch);
	    if (check == true)
	    {
		break;
	    }
	    if (count == pw.length () - 1)
	    {
		System.out.println ("This Password is not valid as there is no upper case");
	    }
	}


	return check;
    }


    boolean PasswordLowerCaseCheck (String pw)
    {
	boolean check = false;
	for (int count = 0 ; count < pw.length () ; count++)
	{
	    char ch = pw.charAt (count);
	    check = Character.isLowerCase (ch);
	    if (check == true)
	    {
		break;
	    }
	    if (count == pw.length () - 1)
	    {
		System.out.println ("This Password is not valid as there is no lower case");
	    }
	}


	return check;
    }


    boolean PasswordNumberCheck (String pw)
    {
	boolean check = false;
	for (int count = 0 ; count < pw.length () ; count++)
	{
	    char ch = pw.charAt (count);
	    check = Character.isDigit (ch);
	    if (check == true)
	    {
		break;
	    }
	    if (count == pw.length () - 1)
	    {
		System.out.println ("This password is not valid as there is no number");
	    }
	}


	return check;
    }




    boolean PasswordWhiteSpaceCheck (String pw)
    {
	boolean check = false;
	for (int count = 0 ; count < pw.length () ; count++)
	{
	    char ch = pw.charAt (count);
	    check = Character.isWhitespace (ch);
	    if (check == true)
	    {
		System.out.println ("This password is not valid as there is a whitespace");
		break;
	    }
	}


	return check;
    }



    boolean SpecialCharacterCheck (String pw)
    {
	boolean check = true;
	for (int count = 0 ; count < pw.length () ; count++)
	{
	    char ch = pw.charAt (count);
	    check = Character.isDigit (ch);
	    if (check == false)
	    {
		check = Character.isLetter (ch);
	    }
	    if (check == false)
	    {

		System.out.println ("This password is not valid as there is special characters");
		break;
	    }
	    else if (check = true)
	    {
	    }
	}


	return check;
    }


    void ClrScreenmethod ()
    {
	for (int x = 0 ; x < 25 ; x++)
	    System.out.println ("\n");
    }
}











