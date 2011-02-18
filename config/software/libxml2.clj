;
; Author:: Adam Jacob (<adam@opscode.com>)
; Copyright:: Copyright (c) 2010 Opscode, Inc.
; License:: Apache License, Version 2.0
;
; Licensed under the Apache License, Version 2.0 (the "License");
; you may not use this file except in compliance with the License.
; You may obtain a copy of the License at
; 
;     http://www.apache.org/licenses/LICENSE-2.0
; 
; Unless required by applicable law or agreed to in writing, software
; distributed under the License is distributed on an "AS IS" BASIS,
; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
; See the License for the specific language governing permissions and
; limitations under the License.
;

(software "libxml2" :source "libxml2-2.7.7"
                    :steps [["env LDFLAGS='-R/opt/opscode/embedded/lib -L/opt/opscode/embedded/lib -I/opt/opscode/embedded/include'" "./configure" "--prefix=/opt/opscode/embedded" "--with-zlib=/opt/opscode/embedded" "--with-readline=/opt/opscode/embedded" "--with-iconv=/opt/opscode/embedded"]
                            ["make"]
                            [ "make" "install"]])
