pandoc planung.md -f markdown -t latex -s -o planung.tex

pdflatex planung.tex

rm *.tex *.aux *.log
