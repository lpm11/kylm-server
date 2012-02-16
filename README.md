kylm-server: Kylm language model server
=======================================

kylm-server は [京都言語モデルツールキット (Kylm)](http://www.phontron.com/kylm/index-ja.html) の N-gram モデルへ [MessagePack-PRC](http://msgpack.org/) を通じてアクセスを可能にした Java ライブラリですが、大したものではありません :P

言語モデルサーバとしての役割もさることながら、副効用として、MessagePack-RPC がサポートする各種言語 (Perl, Ruby, Python, etc.) からのアクセスが容易になっています。

実行可能な jar ファイルは [Downloads](./downloads) に置いてあります。


Dependency
----------
- Kylm (0.0.6 を同梱)
- MessagePack-RPC (2011/12/22 時点での 0.7.0 を同梱)
- 上記ライブラリが依存する各種ライブラリ (Maven より取得可能)

License
-------
kylm-server は LGPL で提供します (これは、Kylm 自体が LGPL である事によります)。ちなみに、MessagePack-RPC は Apache License です。
