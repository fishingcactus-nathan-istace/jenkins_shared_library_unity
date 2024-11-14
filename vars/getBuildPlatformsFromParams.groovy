def call () {
  def platforms = []

  if ( params.BUILD_WIN64 )   { platforms << 'Win64'   } 
  if ( params.BUILD_PS5 )     { platforms << 'PS5'     } 
  if ( params.BUILD_STEAM )   { platforms << 'Steam'   }
  if ( params.BUILD_ANDROID ) { platforms << 'Android' }

  return platforms
}
