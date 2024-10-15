package de.personal.budgetbook.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.personal.budgetbook.objects.BudgetBookItem;

@Repository
public interface BudgetBookItemRepository extends JpaRepository<BudgetBookItem, Long>{
}
