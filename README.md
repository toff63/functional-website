Functional design for a website.
==================

Simple template showing how a website looks like applying functional concepts

The base of Functional design is to use composition everywhere and reuse as much as possible pure functions. Another strong concept in the Haskell language family (Scala, F# and other funcional programming with strong type system) also tend to _type everything_.

##Core type of code

In most application, the code either:
* move data from one layer to another
* transform data to comply with other layer contract
* process business values

##Application design

In the current version, I forced seperated:
* entrypoint: Controller action: [Application.scala](https://github.com/toff63/functional-website/blob/master/app/controllers/Application.scala)
* data flow required to get the data that will be returned: [MyWebsiteDataFlow.scala](https://github.com/toff63/functional-website/blob/master/app/dataflow/MyWebsiteDataFlow.scala)
* Business logic on top of domain model [DomainService.scala] (https://github.com/toff63/functional-website/blob/master/app/service/DomainService.scala) using a trait to be able to reuse business logic. This trait reflect the idea of typing everything.
* Datasource layer: [Domain.scala](https://github.com/toff63/functional-website/blob/master/app/model/Domain.scala)
* Dependency injection via trait: [MyWebsiteComponents.scala](https://github.com/toff63/functional-website/blob/master/app/components/MyWebsiteComponents.scala)


I also created test to demo how to simulate flow stubing the Dao: [MyWebsiteDataFlowTest.scala](https://github.com/toff63/functional-website/blob/master/test/dataflow/MyWebsiteDataFlowTest.scala)

##TODO

I am quiete happy with the current design. However, I would like to add typing to ease the implementation of new features and the creation of more validations. I also need to illustrate the idea of ViewModel.
