    if (false)
    {
         System.out.println("Man in the moon.");
    } else {
         System.out.println("Pie in the sky.");
    }

// exchange the integers in left and right
{   // assume left == 2; right == 3
    int temp;
    temp = left;  // left, temp == 2; right == 3
    left = right; // left, right == 3; temp == 2
    right = temp; //  left == 3; right, temp == 2

}

// exchange the integers in left and right
{   // assume left == 2; right == 3
    left = left - right;  // left == -1; right == 3
    right = right + left; // left == -1; right == 2
    left = right - left;  // left == 3; right == 2
}


