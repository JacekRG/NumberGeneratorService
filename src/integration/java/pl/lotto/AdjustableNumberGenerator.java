package pl.lotto;

import pl.lotto.numbersgenerator.SixNumberGenerable;

import java.util.Set;

public class AdjustableNumberGenerator implements SixNumberGenerable {

//    protected Set<Integer> luckyNumbers = new HashSet<>();
    @Override
    public Set<Integer> randomSixNumbers() {
//        luckyNumbers.add(1);
//        luckyNumbers.add(2);
//        luckyNumbers.add(3);
//        luckyNumbers.add(4);
//        luckyNumbers.add(5);
//        luckyNumbers.add(6);
        return Set.of(1,2,3,4,5,6);
    }

}
