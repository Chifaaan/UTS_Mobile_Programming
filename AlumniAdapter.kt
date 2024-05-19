import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.uts_semangat.Data.AlumniData
import com.uts_semangat.R

class AlumniAdapter(context: Context, private val alumniList: ArrayList<AlumniData>) :
    ArrayAdapter<AlumniData>(context, 0, alumniList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item_alumni, parent, false)
        }

        val currentAlumni = alumniList[position]

        val textViewNim: TextView = itemView!!.findViewById(R.id.textViewNim)
        textViewNim.text = "NIM: ${currentAlumni.nim}"

        val textViewNama: TextView = itemView.findViewById(R.id.textViewNama)
        textViewNama.text = "Nama: ${currentAlumni.nama}"

        // Tambahkan kode untuk menampilkan informasi lainnya sesuai kebutuhan

        return itemView
    }
}