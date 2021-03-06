package controller

import org.scalatra._
import org.scalatra.json.JacksonJsonSupport
import org.json4s.DefaultFormats
import model.Todo
import scala.collection.concurrent.{Map => ConcurrentMap}

class IndexController(todos: ConcurrentMap[Integer, Todo]) extends ScalatraFilter with JacksonJsonSupport {

  implicit val jsonFormats = DefaultFormats

  get("/"){
    html.index()
  }

  get("/all") {
    contentType = formats("json")
    //todo: call a service that handles this logic
    todos.values toSeq
  }

  get("/new") {
    //todo: call a service that handles this logic
    val id = todos.size
    todos.putIfAbsent(id, Todo(id, "Random text for now", false, 1))
    redirect("/")
  }

}
