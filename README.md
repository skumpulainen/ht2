# ht2
Ohjelmaa voidaan käyttää ASCII-grafiikkana esitettyjen kuvien katseluun ja käsittelyyn. Ohjelma
käynnistetään antamalla komentoriviparametrina tiedoston nimi, jolloin tiedoston kuva ladataan
keskusmuistiin. Jos käyttäjän syöttämä tiedoston nimi ei ole samassa hakemistossa ohjelman
kanssa tai hän ei ole antanut tiedoston nimeä, saa hän virheilmoituksen.


# Examples:


-------------------
| A S C I I A r t |
-------------------
printa/printi/info/filter [n]/reset/quit?
printa
*    *  #      *
   *    #*
       ###   *
 *    #####
     o####o  *
    ########
  * o######o
   ##########
   o########o
  ############
  o##########o
 ##############
 o############o
################
       ##
::::::::::::::::
printa/printi/info/filter [n]/reset/quit?

printi
 6 15 15 15 15  6 15 15  0 15 15 15 15 15 15  6
15 15 15  6 15 15 15 15  0  6 15 15 15 15 15 15
15 15 15 15 15 15 15  0  0  0 15 15 15  6 15 15
15  6 15 15 15 15  0  0  0  0  0 15 15 15 15 15
15 15 15 15 15  7  0  0  0  0  7 15 15  6 15 15
15 15 15 15  0  0  0  0  0  0  0  0 15 15 15 15
15 15  6 15  7  0  0  0  0  0  0  7 15 15 15 15
15 15 15  0  0  0  0  0  0  0  0  0  0 15 15 15
15 15 15  7  0  0  0  0  0  0  0  0  7 15 15 15
15 15  0  0  0  0  0  0  0  0  0  0  0  0 15 15
15 15  7  0  0  0  0  0  0  0  0  0  0  7 15 15
15  0  0  0  0  0  0  0  0  0  0  0  0  0  0 15
15  7  0  0  0  0  0  0  0  0  0  0  0  0  7 15
 0  0  0  0  0  0  0  0  0  0  0  0  0  0  0  0
15 15 15 15 15 15 15  0  0 15 15 15 15 15 15 15
11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11
printa/printi/info/filter [n]/reset/quit?

info
16 x 16
# 112
@ 0
& 0
$ 0
% 0
x 0
* 9
o 10
| 0
! 0
; 0
: 16
' 0
, 0
. 0
  109
printa/printi/info/filter [n]/reset/quit?

filter 3
printa/printi/info/filter [n]/reset/quit?
printa
*    *  #      *
        **
       ##*
      ####*
     *#####*
    *######*
    ########
   *########*
   ##########
  o##########o
  ############
 o############o
 o############o
#oo##########oo#
 ::::::##::::::
::::::::::::::::
printa/printi/info/filter [n]/reset/quit?

printa
*    *  #      *
   *    #*
       ###   *
 *    #####
     o####o  *
    ########
  * o######o
   ##########
   o########o
  ############
  o##########o
 ##############
 o############o
################
       ##
::::::::::::::::
printa/printi/info/filter [n]/reset/quit?

quit
Bye, see you soon.
