syntax on
set hidden

"
" Settings
"

set noswapfile                  " Don't use swapfile
set splitright                  " Split vertical windows right to the current windows
set splitbelow                  " Split horizontal windows below to the current windows
set encoding=utf-8              " Set default encoding to UTF-8
set autowrite                   " Automatically save before :next, :make etc.
set autoread                    " Automatically reread changed files without asking me anything

set lazyredraw                  " Wait to redraw 
set ignorecase                  " Search case insensitive...
set smartcase                   " ... but not when search pattern contains upper case characters
set ttyfast

set termguicolors

let &t_8f = "\<Esc>[38;2;%lu;%lu;%lum"
let &t_8b = "\<Esc>[48;2;%lu;%lu;%lum"

set rtp+=/opt/share/vim

if filereadable(expand("~/.base16vim"))
  let base16colorspace=256
  source ~/.base16vim
endif
