# allmd2ml

A Clojure tool designed to reduce the inconvenience of markdown operations.you can turn all markdown file of current directory.

这个clojure编写的工具用来将当前文件夹下的所有md转为html。对于经常书写Blog或者邮件的人很有帮助。

## Usage
if you are  a clojure programer
> * clone this repository
> * cd directory where you have clone
> * type "lein uberjar" in ter
> find jar in target fold , java -jar it wherever you need turn all md to html

$ java -jar thejar  #you will turn all md

## Log
  
### allmd2ml 0.1.0
 *  添加将文件夹下所有的md转为html功能

### allmd2ml 0.3.0
 *  修正部分0.2.0函数
 *  添加指定文件转换功能

### allmd2ml 1.0.0
 * 取消指定文件转换功能,认为单一md的转换一般可以在编辑器中自行完成
 * 取消日志功能
 * 仅保留转换目录下所有md功能，且经过思考后认为不应该再增加任何功能

## License
 
Copyright © 2014 刘子方

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
