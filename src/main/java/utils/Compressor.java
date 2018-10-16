package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Compressor {
	
	final static char[] CHARACTER_SET = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	final static String HASHING_ALGORITHM = "SHA1";
	final static int CHARACTERSET_SIZE = 36;
	
	/**
	 * @param inputString that needs to be compressed to a unique value
	 * @param compressionSize is the output string size that you desire to have (input string in compressed to this size)
	 * @return compressed string of desired compressionSize
	 * @throws NoSuchAlgorithmException
	 */
	public static String getCompressedString(String inputString, final int compressionSize) throws NoSuchAlgorithmException {

		int checkSumMod = 0;
		
		String hashedOutput = getHashedStringAsHex(inputString, HASHING_ALGORITHM);
		char[] inputArray = hashedOutput.toCharArray();
		int inputArraySize = inputArray.length;
		int totalChecksum = 0;
		
		int compressableSize = getCompressableSize(compressionSize);
		int partitionSize = inputArraySize/compressableSize;
		
		int[] inputChecksumArray = new int[inputArraySize];
		int[] compressedChecksumArray = new int[compressableSize];
		
		char[] finalChecksumStringArray = new char[compressionSize];
		
		//Multiplying ASCII value of the character with its position
		for(int i=1; i<= inputArraySize; i++){
			inputChecksumArray[i-1] = inputArray[i-1] * i; 
		}
		
		//Adding partition blocks to form the final checksum array
		for(int i=0, j=0 ; i<inputArraySize; j++){
			int partitionChecker = 0;
			int partitionChecksum = 0;
			
			while(partitionChecker < partitionSize){
				partitionChecksum += inputChecksumArray[i++];
				partitionChecker++;
			}
			compressedChecksumArray[j] = partitionChecksum;
			totalChecksum += partitionChecksum;
		}
		
		//Mapping checksums with defined alphanumeric character set by calculating modulo
		for(int i=0; i<compressionSize ; i++){
			if(i == (compressionSize-1)){
				checkSumMod = totalChecksum % CHARACTERSET_SIZE ;
			}else{
				checkSumMod = compressedChecksumArray[i] % CHARACTERSET_SIZE;
			}
			finalChecksumStringArray[i] = CHARACTER_SET[checkSumMod];
		}
		return new String(finalChecksumStringArray);
	}

	private static String getHashedStringAsHex(String input, String hashingAlgorithm) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance(hashingAlgorithm);
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	private static int getCompressableSize(int compressionSize){
		int modulo = compressionSize % 10;
		return modulo !=0 ? compressionSize + 10 - (modulo) : compressionSize;
	}
}
