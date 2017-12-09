package ifood.score.menu;

import ifood.score.model.ProcessingStatus;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MenuRelevanceRepository {

  private Set<MenuRelevance> relevances = Collections.synchronizedSet(new HashSet<>());

  public void addRelevances(List<MenuRelevance> relevancesCreated) {
    this.relevances.addAll(relevancesCreated);
  }

  public void updateRelevances(Set<MenuRelevance> relevances) {
    Set<MenuRelevance> newSet = this.relevances.stream()
        .filter(item -> !relevances.contains(item))
        .collect(Collectors.toSet());

    newSet.addAll(relevances);

    this.relevances = newSet;
  }

  public void updateRelevance(MenuRelevance relevance) {
    this.relevances.remove(relevance);
    this.relevances.add(relevance);
  }

  public Set<MenuRelevance> getRelevancesByStatus(ProcessingStatus status, int limit) {
    return this.relevances.stream()
        .filter(r -> status.equals(r.getProcessingStatus()))
        .limit(limit)
        .collect(Collectors.toSet());
  }

  public void removeRelevance(MenuRelevance relevance) {
    this.relevances.remove(relevance);
  }

}