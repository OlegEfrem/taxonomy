package com.timeout.taxonomy.helpers

import com.timeout.taxonomy.model.{Descendants, Node, Tag}

import scala.util.Random

trait TestData {
  val notExistingId = "notExistingId"
  val oneNodeId = "oneNodeId"
  val twoSameNodeIdInSameBranch = "twoSameIdNodeInSameBranch"
  val twoSameNodeIdInDifferentBranches = "twoSameIdNodeInDifferentBranches"
  val oneTagId = "oneTagId"
  val twoSameTagIdInSameBranch = "twoSameIdNodeInSameBranch"
  val twoSameTagInDifferentBranches = "twoSameIdNodeInDifferentBranches"

  def createTwoSameNodeIdInSameBranch(): Node = {
    createNode(
      id = twoSameNodeIdInSameBranch,
      descendants = Some(
        Descendants(List(
          createNode(
            id = twoSameNodeIdInSameBranch,
            parent = Some(createNode(id = twoSameNodeIdInSameBranch)))
        )))
    )
  }

  def createNode(id: String = randomString, tagId: String = randomString, parent: Option[Node] = None, descendants: Option[Descendants] = None) =
    Node(
      id = id,
      tag = Tag(id = tagId, translations = Seq()),
      parent = parent,
      descendants = descendants
    )

  def createTwoSameNodeIdInDifferentBranches(): List[Node] = {
    val randomNodeId = randomString
    List(
      createNode(
        id = twoSameNodeIdInDifferentBranches,
        descendants = Some(Descendants(List(randomNode)))
      ),
      createNode(
        id = randomNodeId,
        descendants = Some(
          Descendants(List(
            createNode(
              id = twoSameNodeIdInDifferentBranches,
              parent = Some(createNode(id = randomNodeId)),
              descendants = Some(Descendants(List(randomNode)))
            )))
        )
      )
    )
  }

  def createTwoSameTagIdInSameBranch(): Node = {
    createNode(
      tagId = twoSameNodeIdInSameBranch,
      descendants = Some(
        Descendants(List(
          createNode(
            tagId = twoSameNodeIdInSameBranch,
            parent = Some(createNode(id = twoSameNodeIdInSameBranch)))
        )))
    )
  }

  def createTwoSameTagInDifferentBranches(): List[Node] = {
    val randomNodeId = randomString
    List(
      createNode(
        tagId = twoSameTagInDifferentBranches,
        descendants = Some(Descendants(List(randomNode)))
      ),
      createNode(
        tagId = randomNodeId,
        descendants = Some(
          Descendants(List(
            createNode(
              tagId = twoSameTagInDifferentBranches,
              parent = Some(createNode(id = randomNodeId)))
          )))
      )
    )
  }

  def randomNode = createNode(randomString)

  private def randomString = Random.nextString(10)

}
