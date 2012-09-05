/**************************************************************************
File: hellomouse.c
Does: basic drawing and interaction in OpenGL/GLUT
Author: gem, based on hwa's
Date: 01/08/08
**************************************************************************/
/**********
notes:
VS users: include opengl/glut.h below instead of GL/glut.h
OSX users: include glut/glut.h below instead of GL/glut.h
**********/

#include <glut/glut.h>
#include <stdio.h>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <cmath>

#include "timer.h"

using namespace std;

double laps[100];               // the array of shapes that we'll use throughout
double splits[100];               // the array of shapes that we'll use throughout
double best_splits[100];               // the array of shapes that we'll use throughout
double best_run[100];               // the array of shapes that we'll use throughout
string lap_names[100];


int curr_count, start, lap_count, done, id, stop, total_laps;
double seconds, slowdown;

/*Typical OpenGL/GLUT Main function */ 
int main(int argc, char **argv) { /* program arguments */

  /* initialize GLUT and OpenGL; Must be called first */
  glutInit( &argc, argv ) ;
  
  /* our own initializations; we'll define these setup procedures */
  glut_setup() ;  
  gl_setup() ;
  my_setup();

  /* turn control over to GLUT */
  glutMainLoop() ;

  return(0) ; /* make the compiler happy */
}



/* Typical function to set up GLUT*/
void glut_setup(void) {

  /* specify display mode -- here we ask for double buffering and RGB coloring */
  glutInitDisplayMode (GLUT_DOUBLE |GLUT_RGB);

  /* make a window of size window_w by window_h; title of "GLUT Basic Interaction" placed at top left corner */
  glutInitWindowSize(100, 100);
  glutInitWindowPosition(300,300);
  glutCreateWindow("zps6 Assignment 1");

  /*initialize typical callback functions */
  glutDisplayFunc( my_display );
  glutReshapeFunc( my_reshape ); 
  glutIdleFunc( my_idle );
  glutMouseFunc( my_mouse );  
  glutKeyboardFunc( my_keyboard );  
  glutTimerFunc( 10, my_TimeOut, 0);/* schedule a my_TimeOut call with the ID 0 in 20 seconds from now */

  return ;
}

/* Typical function to set up OpenGL */
/* For now, ignore the contents of function */
void gl_setup(void) {

  /* specifies a background color: black in this case */
  glClearColor(0,1,0,0) ;

  /* setup for simple 2d projection */
  glMatrixMode (GL_PROJECTION);
  glLoadIdentity();
  /* map unit square to viewport window */
  gluOrtho2D(0.0, 1.0, 0.0, 1.0); 

  return ;
}


void my_idle(void) {
  return ;
}


void my_setup(void) {
    printf("*****HEY CLICK ME!!*****\nEnter a (up to 3 digit) run id: ");
    scanf("%d", &id);
    printf("\n\nNow click on the GLUT window to start timing. Good luck.\n");
    printf("Alternatively, right click to make a route list of laps.\n\n\n");
    
    file_read();
    file_read_2();

    return ;
}

void my_display(void) {
  return ;
}

void my_reshape(int w, int h) {
  return ;
}

void my_mouse(int b, int s, int x, int y) {
    
  switch (b) {            /* b indicates the button */
  case GLUT_LEFT_BUTTON:
    if (s == GLUT_UP)  {      // let go of the button
        glutTimerFunc(10, my_TimeOut, 1); // keeps the timer moving, at timer_speed ms
        start = 1;
        printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nHere we Goooooooo! \n\n\n\n\n\n\n");
        curr_count = 0;


    }
  break;
  case GLUT_RIGHT_BUTTON:
    if (s == GLUT_UP)  {      // let go of the button
        
        getline(cin, lap_names[lap_count]);
        string temp;

        while (!done) {
          printf("*****CLICK BACK ON ME******\n");
          printf("Enter a lap name, or quit to stop: ");
          getline(cin, temp);
          if (temp == "quit") {
            done = 1;
            printf("Done. Now left click on the glut window to play!\n");
          }
          else {
            lap_names[lap_count] = temp;
            lap_count++;
          }
        }
    }
  }
  return ;
}

void my_keyboard(unsigned char c, int x, int y) {
  
  if (c == ' ' || curr_count + 1 == total_laps) {
    stop = 1;
    start = 0;
    if (curr_count + 1 == total_laps) {
      laps[curr_count] = seconds;
      splits[curr_count] = seconds - laps [curr_count - 1];

      curr_count++;
    }
    finish();

  }

  if (c == '`') {
    curr_count--;
  }

  if (start && c != '`') {
    laps[curr_count] = seconds;
    printf("\n");
    if (curr_count == 0) {
      splits[0] = seconds;
    }
    else {
      splits[curr_count] = seconds - laps [curr_count - 1];
    }

      curr_count++;

    file_write();
  }


  return ;
}

void finish()
{
  for (int p = 0; p < curr_count; p++) {
    if (splits[p] < best_splits[p]) {
      best_splits[p] = splits[p];
      printf("\nNEW BEST SPLIT ON %s|%02d:%05.2f!!\n", lap_names[p].c_str(), (int)(splits[p])/60, fmod(splits[p], 60));
    }
  }

  if (laps[curr_count - 1] < best_run[curr_count - 1] && curr_count == total_laps) {
    for (int p = 0; p < curr_count; p++) {
      best_run[p] = laps[p];
    }
    printf("\nNEEEEW RECORD |%02d:%05.2f!!\n", (int)(best_run[curr_count - 1])/60, fmod(best_run[curr_count - 1], 60));
  }

  file_write();

  int overwrite = 0;
  printf("Overwrite? (1 for yes, otherwise no)");
  cin >> overwrite;
  if (overwrite == 1) {
    file_write_2();
    file_write_3();
  }

}

void file_write()
{
    ostringstream filename;
    filename << "run_";
    filename << setfill( '0' ) << setw( 3 ) << id;
    filename << ".txt";

    ofstream myfile( filename.str().c_str() );

    if (myfile.is_open())
    {
        for (int i = 0; i < curr_count; i++) {
          myfile << lap_names[i];
          myfile << ":";
          myfile << laps[i];  
          myfile << "\n";
        }

        myfile.close();
    }   

    return ;

}

void file_write_2()
{
    ofstream myfile("best_run.txt");

    if (myfile.is_open())
    {
        for (int i = 0; i < curr_count; i++) {
          myfile << lap_names[i];
          myfile << ":";
          myfile << best_run[i];  
          myfile << "\n";
        }

        myfile.close();
    }   

    return ;

}

void file_write_3()
{
    ofstream myfile("best_splits.txt");

    if (myfile.is_open())
    {
        for (int i = 0; i < curr_count; i++) {
          myfile << lap_names[i];
          myfile << ":";
          myfile << best_splits[i];  
          myfile << "\n";
        }

        myfile.close();
    }   

    return ;

}

void file_read_2()
{
  ifstream infile;
  infile.open ("best_run.txt");
  string temp, temp1;
  int toggle = 1, e = 0;;
  while(infile.good()) // To get you all the lines.
  {
    getline(infile, temp); // Saves the line in STRING.

    stringstream stream(temp);
    while( getline(stream, temp1, ':') ) {
      if (toggle == 1) {
        lap_names[e] = temp1;

        toggle *= -1;
      }
      else
      {
        best_run[e] = strtod(temp1.c_str(), NULL);
        toggle *= -1;
        e++;
      }
    }
  }
  infile.close();

  return ;
}

void file_read()
{
  ifstream infile;
  infile.open ("best_splits.txt");
  string temp, temp1;
  int toggle = 1;
  while(infile.good()) // To get you all the lines.
  {
    getline(infile, temp); // Saves the line in STRING.

    stringstream stream(temp);
    while( getline(stream, temp1, ':') ) {
      if (toggle == 1) {
        lap_names[curr_count] = temp1;

        toggle *= -1;
      }
      else
      {
        best_splits[curr_count] = strtod(temp1.c_str(), NULL);
        toggle *= -1;
        curr_count++;
        total_laps++;
      }
    }
  }
  infile.close();

  return ;
}

void my_TimeOut(int id) { 

  if (id == 1) {
    seconds += .1;

    if (stop !=1) {
    printf("\r%-8s|%02d:%05.2f",lap_names[curr_count].c_str(), (int)seconds/60, fmod(seconds, 60));
    printf("%10s%02d:%05.2f", "split|", (int)(seconds - laps [curr_count - 1])/60, fmod(seconds - laps [curr_count - 1], 60));
    printf("%13s%02d:%05.2f", "pb split|", (int)best_splits[curr_count]/60, fmod(best_splits[curr_count], 60)); 
    printf("%11s%02d:%05.2f", "pb run|", (int)best_run[curr_count]/60, fmod(best_run[curr_count], 60)); 

    fflush(stdout);

    
    glutTimerFunc(100, my_TimeOut, 1); // keeps the timer moving, at timer_speed ms
    }
  }

  return ;
}


