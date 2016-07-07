package com.timeout.taxonomy.model

/**
  * @param id node name, eg: shows
  **/
case class Node(id: String, tag: Tag, parent: Option[Node], descendants: Option[Descendants]) {

  def descendantsAsNodes: List[Node] =
    descendants.map(_.nodes).getOrElse(List())

}

case class Descendants(nodes: List[Node])

/**
  * @param id tag name, eg: chinese
  **/
case class Tag(id: String, translations: Seq[Translation])

/**
  * @param id    translation language, eg: en_GB
  * @param value word translation, eg: Chinese
  **/
case class Translation(id: String, value: String)
