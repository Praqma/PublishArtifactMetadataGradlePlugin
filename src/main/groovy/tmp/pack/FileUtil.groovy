package tmp.pack
/**
 * Takes file and return a hash sum as a string
 */
import java.security.MessageDigest

public class FileUtil {

    public static String md5(File file) {
        def hash = MessageDigest.getInstance( 'MD5' ).with {
            file.eachByte( 8192 ) { bfr, num ->
                update bfr, 0, num
            }
            it.digest()
        }
        return new BigInteger( 1, hash ).toString( 16 ).padLeft( 32, '0' )
    }
}
