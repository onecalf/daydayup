# Http 缓存相关笔记

### Http缓存分为：强制缓存、对比缓存

* 强制缓存：强制缓存是一旦缓存可用直接返回缓存数据，如果不可用请求server。优先级：强制缓存>对比缓存
	* Expires：http 1.0 字段 1.1以后不建议使用。废弃。 
	* Cache-Control ：
		* private 客户端可缓存
		* public  客户端和服务器都可缓存
		* max-age=xxx 缓存的内容将在xxx秒后过期
		* no-cache 需要使用对比缓存来验证缓存数据
		* no-store 所有内容都不会缓存，强制缓存，对比缓存都不生效
* 对比缓存：都要请求server验证缓存是否可用，server返回304可用，直接读取缓存，如不可用，则server返回新的缓存规则和最新的数据，然后客户端缓存最新数据和缓存规则。
* Last-Modified / If-Modified-Since
	* Last-Modified：服务端相应header中告诉客户端 资源的最后修改时间。
	* If-Modified-Since：客户端请求header中告诉服务端上次请求的资源的最后修改时间。
	* 若资源的最后修改时间大于If-Modified-Since，说明资源又被改动过，则响应整片资源内容，返回状态码200。若资源的最后修改时间小于或等于If-Modified-Since，说明资源无新修改，则响应HTTP 304，告知客户端缓存可用。
* Etag / If-None-Match
	* 优先级高于Last-Modified.
	* Etag 类似于资源的md5码，如果资源变化则ETag变化。
	* 服务器响应头重返回 资源 Etag。客户端缓存数据和Etag。
	* 客户端请求头带 If-None_Match 字段请求服务器，如匹配成功则直接返回304 客户端直接读取缓存，如匹配失败则返回200 ，最新的数据+最新的缓存规则。




