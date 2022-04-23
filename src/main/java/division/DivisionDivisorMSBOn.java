package division;

import core_architecture.CircuitNode;
import core_architecture.DigitalCircuit;

public class DivisionDivisorMSBOn extends DigitalCircuit {
    private final BitDivision[] bitDivisions;

    private final int dividendBits;

    public DivisionDivisorMSBOn(String label, int dividendBits, int divisorBits) {
        super(label, dividendBits+divisorBits, dividendBits+1);

        this.dividendBits = dividendBits;

        int bitDelta = dividendBits-divisorBits+1;
        bitDivisions = new BitDivision[bitDelta];

        for (int i = 0; i < bitDelta; i++) {
            bitDivisions[i] = new BitDivision(label + " DivBit_"+i, bitDelta);
            transistorCount += bitDivisions[i].getTransistorCount();

            bitDivisions[i].assignInput(bitDelta, GND);
            if (i==0) {
                bitDivisions[i].assignInput(0, GND);
                for (int j = 0; j < divisorBits; j++) {
                    bitDivisions[i].assignInput(1+j, getDividendBit(j));
                    bitDivisions[i].assignInput(bitDelta+1+j, getDivisorBit(j));
                }
            } else {
                for (int j = 0; j < divisorBits; j++) {
                    bitDivisions[i].assignInput(j, bitDivisions[i-1].getOutput(2+j));
                    bitDivisions[i].assignInput(bitDelta+1+j, getDivisorBit(j));
                }
                bitDivisions[i].assignInput(divisorBits, getDividendBit(i+divisorBits-1));
            }
            bitDivisions[i].assignOutput(0, getInternalOutput(i));
        }

        for (int i = 0; i < divisorBits; i++) {
            bitDivisions[bitDelta-1].assignOutput(i+2, getInternalOutput(bitDelta+i));
        }
    }

    private CircuitNode getDivisorBit(int i) {
        return getInternalInput(i+dividendBits);
    }

    private CircuitNode getDividendBit(int i) {
        return getInternalInput(i);
    }

    @Override
    protected void evaluateCircuit() {
        for (BitDivision bitDiv : bitDivisions) {
            bitDiv.evaluate();
        }
    }
}
