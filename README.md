The classic MVVM + DDD architecture
K.Coroutines is used as thread processing
Unit tests, also included
Business logic in atomic entities - UseCase

_The basic idea is simple and straightforward code that can be supported and scaled by any developer._

## Assumptions:
1) Fragments and navigation are not used - over-engineering for this task
2) Database is not used
3) A base theme from the application template is used
4) Refusal of integration testing because of the simplicity of the task
5) Primitive junit test for converters

## What can be improved, in the case of scaling the task:
1) Use RecyclerViewPreloader for dynamic loading images in RecyclerView
2) Use database orm as Datasource for repository and store data
3) Use offline search by all data instead of using api
4) Use SingleActivity pattern
5) Allocate the work with DI into a separate unit
6) Combined search not only by name
7) Proguard improvements