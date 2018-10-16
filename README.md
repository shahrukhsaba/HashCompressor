#string-compressor
UPDATED HASHING ALGORITHM (To get a unique 7 character length string) :
1. Digest the input external id with SHA1 hash to generate 40 character hexadecimal output.
2. Convert the String into character array.
3. Take a new integer array and populate its elements by multiplying ASCII value of characters with its position as below:
CHARACTER ARRAY	ASCII	POSITION	INTEGER ARRAY(ASCII * POSITION)
S	083	1	83
A	065	2	130
M	120	3	360
P	123	4	492
L	119	5	595
E	112	6	672
0	048	7	336
0	048	8	384
1	092	9	644
4. As above we will get an integer array of size 40 and now we reduce it down to 10 by summing adjacent 4 numbers.
5. Say we get the below integer array of size 10 by summing logic.
Position	1	2	3	4	5	6	7	8	9	10
Sum	        786	546	875	645	256	768	763	453	886	985
The total sum of above is : 6963
6. Now since we need a string on 7 character length, we further reduce the above array in step 5 by trimming off all numbers after position 7 and replace 7th position by the total sum. As below:
Position	1	2	3	4	5	6	7
Sum	        786	546	875	645	256	768	6963
7. The last step is to map the sum with ALPHANUMERIC characters of desired choice. In our case the character set is :
CHAR SET	POSITION
0	0
1	1
2	2
3	3
4	4
5	5
6	6
7	7
8	8
9	9
A	10
B	11
C	12
D	13
E	14
F	15
G	16
H	17
I	18
J	19
K	20
L	21
M	22
N	23
O	24
P	25
Q	26
R	27
S	28
T	29
U	30
V	31
W	32
X	33
Y	34
Z	35
8. Since the character set size is 36. Hence, we do a modulo of the sums calculated in step 6 with 36 to get a number corresponding to the position of the character in our character set. As below:
FINAL NUMBER ARRAY	MOD 36	MAPPED CHARACTER IN MY SET
786	 30	U
546	 6	6
875	 11	B
645	 33	X
256	 4	4
768	 12	C
6963 15	F
NOTE: Since we have to choose 7 characters out of 36 distinct character sets. The total number of unique combinations possible would be: C(36,7) = 8,347,680.
