package de.beheh.luebeck.cuckoo;

/////////////////////////////////////////////////////////////////////////////////////
import java.math.*;
/************************************************************************
 * *
 * Implementation of the AKS Algorithm for Primality Testing *
 * *
 ************************************************************************/
public class AKS 
{
    private int k, count, facts[], primes[], N;
    private int l;
    private boolean comp[];


    public boolean isSmPrime(int n)
    {
        if(comp[n] == false) return true;
        else return false;
    }

    public int largestFact(int n)
    {
        int i;
        i = n;
        if(i == 1) return i;
        while(i > 1)
            {
                while( comp[i] == true ) i--;
                if(n % i == 0) return i;
                i--;
            }
        return n;
    }

    public double bigLog(String s)
    {
        String t;
        int l;
        double d, r;
        l = s.length();
        t = "." + s;
        d = Double.parseDouble(t);
        r = Math.log10(d) + l;
        return r;
    }

    public double bigLog(BigInteger s)
    {
        String t;
        int l;
        double d, r;
        t = "." + s.toString();
        l = t.length() - 1;
        d = Double.parseDouble(t);
        r = Math.log10(d) + l;
        return r;
    }

    public boolean isPowerOf(BigInteger n, int i)
    {
        int l;
        double len;
        BigInteger low, high, mid, res;
        low = new BigInteger("10");
        high = new BigInteger("10");
        len = (n.toString().length()) / i;
        l = (int) Math.ceil(len);
        low = low.pow(l - 1);
        high = high.pow(l).subtract(BigInteger.ONE);
        while(low.compareTo(high) <= 0)
            {
                mid = low.add(high);
                mid = mid.divide(new BigInteger("2"));
                res = mid.pow(i);
                if(res.compareTo(n) < 0)
                    {
                        low = mid.add(BigInteger.ONE);
                    }
                else if(res.compareTo(n) > 0)
                    {
                        high = mid.subtract(BigInteger.ONE);
                    }
                else if(res.compareTo(n) == 0)
                    {
                        System.out.println("res = " + res + " mid = " + mid);
                        return true;
                    }
            }
        return false;
    }

    boolean isPower(BigInteger n)
    {
        int l, i;
        l = (int) bigLog(n);
        for(i = 2; i < l; i++)
            {
                if(isPowerOf(n, i))
                    {
                        return true;
                    }
            }
        return false;
    }

    long mPower(long x, long y, long n)
    {
        long m, p, z;
        m = y;
        p = 1;
        z = x;
        while(m > 0)
            {
                while(m % 2 == 0)
                    {
                        m = (long) m / 2;
                        z = (z * z) % n;
                    }
                m = m - 1;
                p = (p * z) % n;
            }
        return p;
    }

    BigInteger mPower(BigInteger x, BigInteger y, BigInteger n)
    {
        BigInteger m, p, z, two;
        m = y;
        p = BigInteger.ONE;
        z = x;
        two = new BigInteger("2");
        while(m.compareTo(BigInteger.ZERO) > 0)
            {
                while( ( (m.mod(two)).compareTo(BigInteger.ZERO) ) == 0)
                    {
                        m = m.divide(two);
                        z = (z.multiply(z)).mod(n);
                    }
                m = m.subtract(BigInteger.ONE);
                p = (p.multiply(z)).mod(n);
            }
        return p;
    }


    public void Sieve()
    {
        int i, j;
        N = 1000000;
        comp = new boolean[N+1];
        comp[1] = true;
        for(i = 2; i * i <= N; i++)
            {
                if(comp[i] != true)
                    {
                        for(j = i * i; j <= N; j += i)
                            {
                                comp[j] = true;
                            }
                    }
            }
    }


    public boolean isPrime(BigInteger n)
    {
        int tr, q, tm, ai, up, o;
        BigInteger r, t, x, lh, rh, fm, yai;
        l = (int) bigLog(n);
        if( isPower(n) ) return false;
        r = new BigInteger("2");
        tr = r.intValue();
        while( r.compareTo(n) < 0 )
            {
                if( (r.gcd(n)).compareTo(BigInteger.ONE) != 0 ) return false;
                tr = r.intValue();
                if( isSmPrime(tr) )
                    {
                        q = largestFact(tr - 1);
                        o = (int) (tr - 1) / q;
                        tm = (int) (4 * (Math.sqrt(tr)) * l);
                        t = mPower(n, new BigInteger("" + o), r);
                        if( q >= tm && (t.compareTo(BigInteger.ONE)) != 0 ) break;
                    }
                r = r.add(BigInteger.ONE);
            }
        x = new BigInteger("2");
        fm = (mPower(x, r, n)).subtract(BigInteger.ONE);
        up = (int) (2 * Math.sqrt(tr) * l);
        for(ai = 1; ai < up; ai++)
            {
                yai = new BigInteger("" + ai);
                lh = (mPower(x.subtract(yai), n, n)).mod(n);
                rh = (mPower(x, n, n).subtract(yai)).mod(n);
                if(lh.compareTo(rh) != 0) return false;
            }
        return true;
    }
    public AKS()
    {
        Sieve();
    }
    public static void main(String args[])
    {
        AKS bp = new AKS();
        System.out.println(bp.isPrime(BigInteger.valueOf(42)));
        


    }
}
/*****************************************************************
* This code has been developed as a course work of *
* Computer Science and Engineering Department *
* The University of Asia Pacific *
* *
* Abdullah Al Zakir Hossain, aazhbd@yahoo.com *
* The copy is redistributable *
*****************************************************************/