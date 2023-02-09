package pl.lotto.numbersgenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface LuckyNumbersRepository extends MongoRepository<LuckyNumbersDocument, LocalDateTime> {
    Optional<LuckyNumbersDocument> findByDrawDate(LocalDateTime date);
}
