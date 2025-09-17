// Name the revised Rational class RationalUsingBigInteger 
class RationalUsingBigInteger extends Number implements Comparable<RationalUsingBigInteger> {
  private BigInteger numerator;
  private BigInteger denominator;

  public RationalUsingBigInteger(BigInteger numerator, BigInteger denominator) {
    if (denominator.equals(BigInteger.ZERO)) {
      throw new ArithmeticException("Denominator cannot be zero.");
    }

    // Simplify
    BigInteger gcd = numerator.gcd(denominator);
    numerator = numerator.divide(gcd);
    denominator = denominator.divide(gcd);

    // Make denominator positive
    if (denominator.compareTo(BigInteger.ZERO) < 0) {
      numerator = numerator.negate();
      denominator = denominator.negate();
    }

    this.numerator = numerator;
    this.denominator = denominator;
  }

  public BigInteger getNumerator() {
    return numerator;
  }

  public BigInteger getDenominator() {
    return denominator;
  }

  public RationalUsingBigInteger add(RationalUsingBigInteger other) {
    BigInteger newNumerator = this.numerator.multiply(other.denominator).add(other.numerator.multiply(this.denominator));
    BigInteger newDenominator = this.denominator.multiply(other.denominator);
    return new RationalUsingBigInteger(newNumerator, newDenominator);
  }

  public RationalUsingBigInteger subtract(RationalUsingBigInteger other) {
    BigInteger newNumerator = this.numerator.multiply(other.denominator).subtract(other.numerator.multiply(this.denominator));
    BigInteger newDenominator = this.denominator.multiply(other.denominator);
    return new RationalUsingBigInteger(newNumerator, newDenominator);
  }

  public RationalUsingBigInteger multiply(RationalUsingBigInteger other) {
    BigInteger newNumerator = this.numerator.multiply(other.numerator);
    BigInteger newDenominator = this.denominator.multiply(other.denominator);
    return new RationalUsingBigInteger(newNumerator, newDenominator);
  }

  public RationalUsingBigInteger divide(RationalUsingBigInteger other) {
    if (other.numerator.equals(BigInteger.ZERO)) {
      throw new ArithmeticException("Cannot divide by zero.");
    }

    BigInteger newNumerator = this.numerator.multiply(other.denominator);
    BigInteger newDenominator = this.denominator.multiply(other.numerator);
    return new RationalUsingBigInteger(newNumerator, newDenominator);
  }

  @Override
  public String toString() {
    if (denominator.equals(BigInteger.ONE)) {
      return numerator.toString();
    } else {
      return numerator + "/" + denominator;
    }
  }

  @Override
  public double doubleValue() {
    return numerator.doubleValue() / denominator.doubleValue();
  }

  @Override
  public float floatValue() {
    return numerator.floatValue() / denominator.floatValue();
  }

  @Override
  public int intValue() {
    return numerator.divide(denominator).intValue();
  }

  @Override
  public long longValue() {
    return numerator.divide(denominator).longValue();
  }

  @Override
  public int compareTo(RationalUsingBigInteger other) {
    // Cross multiply to compare: a/b ? c/d → ad ? bc
    BigInteger left = this.numerator.multiply(other.denominator);
    BigInteger right = other.numerator.multiply(this.denominator);
    return left.compareTo(right);
  }
}
