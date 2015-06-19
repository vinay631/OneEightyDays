from string import translate,maketrans,punctuation
from itertools import chain
from nltk import PunktSentenceTokenizer
import datetime 
import re
from gensim.models import Word2Vec

filename = 'all_commentary.txt'
data = []

with open(filename, 'r') as fread:
    for line in fread:
    data.append(line.rstrip())

def tokenize(sentence):
    tokens = []
    sentence = sentence.decode('utf-8')
    s = tknr.tokenize(sentence)
    cleaned_words = [list(translate(re.sub(r'[0-9]|\-|\\~|\`|\@|\$|\%|\^|\&|\*|\(|\)|\_|\=|\[|\]|\\|\<|\<|\>|\?|\/|\;|\\.',' ',sentence).lower().encode('utf-8'),Trans).split()) for sentence in s]
    for cleaned_word in cleaned_words:
        tokens.append(cleaned_word)
    tokens = list(chain(*tokens))
    return tokens

sentences = []
for d in data:
    sentences.append(tokenize(d))

model = Word2Vec(sentences)

print model.most_similar('missed')
