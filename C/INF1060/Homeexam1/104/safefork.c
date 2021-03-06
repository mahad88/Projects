#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

#include <sys/errno.h>


extern int errno;

#define MAX_PROCESSES 6

static int n_processes(void)
{
  return system("exit `/bin/ps | /usr/bin/wc -l`")/256;
}


pid_t safefork(void)
{
  static int n_initial = -1;
  
  if (n_initial == -1)
    n_initial = n_processes();
  else if (n_processes() >= n_initial+MAX_PROCESSES) {
    sleep(2);
    return -EAGAIN;
  }
  
  return fork();
}
