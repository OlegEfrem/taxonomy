package com.timeout.taxonomy.repository

import com.timeout.taxonomy.model.Node
/**
  * Trait for repository implementations
  * */
trait TaxonomyRepository {
  def tree(): Set[Node]
}