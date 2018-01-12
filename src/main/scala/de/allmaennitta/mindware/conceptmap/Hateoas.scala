package de.allmaennitta.mindware.conceptmap

import org.springframework.hateoas.ResourceSupport
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

@JsonCreator
class Hateoas(@JsonProperty("content") var _content: String) extends ResourceSupport {
  def content: String = _content
}

/*
  @RequestMapping(value = Array("/node/all"), method = Array(RequestMethod.GET))
  def getAllNodes: util.Map[String, lang.Iterable[Node]] = {
    val nodes: util.Map[String, lang.Iterable[Node]] =
      JavaConverters.mapAsJavaMap(HashMap("nodes" -> nodeRepository.findAll()))
    nodes
  }

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting extends ResourceSupport {

    private final String content;

    @JsonCreator
    public Greeting(@JsonProperty("content") String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
 */